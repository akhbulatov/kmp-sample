package org.example.kmpsample.repos

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.divider.MaterialDividerItemDecoration
import domain.model.Repo
import kotlinx.coroutines.launch
import org.example.kmpsample.R
import org.example.kmpsample.databinding.FragmentReposBinding

class ReposFragment : Fragment(R.layout.fragment_repos) {

    private val viewModel by viewModels<ReposViewModel>(
        factoryProducer = { ReposViewModel.Factory }
    )

    private val repoAdapter by lazy {
        RepoAdapter()
    }

    private var _binding: FragmentReposBinding? = null
    private val binding: FragmentReposBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        _binding = FragmentReposBinding.bind(requireView())
        with(binding) {
            reposList.apply {
                addItemDecoration(
                    MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL)
                )
                adapter = repoAdapter
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

    private fun setUiState(uiState: ReposUiState) {
        setEmptyProgress(show = uiState.emptyProgress)
        setEmptyError(error = uiState.emptyError)
        setRepos(repos = uiState.repos)
    }

    private fun setEmptyProgress(show: Boolean) {
        binding.reposEmptyProgressBar.isVisible = show
    }

    private fun setEmptyError(error: String?) {
        binding.reposErrorView.text = error
    }

    private fun setRepos(repos: List<Repo>) {
        repoAdapter.submitList(repos)
    }

    override fun onDestroyView() {
        binding.reposList.adapter = null
        _binding = null
        super.onDestroyView()
    }
}