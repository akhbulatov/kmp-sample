package org.example.kmpsample.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.example.kmpsample.R
import org.example.kmpsample.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val viewModel by viewModels<AuthViewModel>(
        factoryProducer = { AuthViewModel.Factory }
    )

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        _binding = FragmentAuthBinding.bind(requireView())
        with(binding) {
            authSaveButton.setOnClickListener {
                viewModel.onSaveClick(
                    login = authLoginInput.text?.toString().orEmpty()
                )
            }
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { setUiState(it) }
            }
        }
    }

    private fun setUiState(uiState: AuthUiState) {
        binding.authLoginLabel.text = "Login: ${uiState.login}"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}