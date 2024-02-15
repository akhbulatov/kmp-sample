package org.example.kmpsample.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.divider.MaterialDividerItemDecoration
import domain.model.SearchRecentQuery
import kotlinx.coroutines.launch
import org.example.kmpsample.R
import org.example.kmpsample.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>(
        factoryProducer = { SearchViewModel.Factory }
    )

    private val recentQueryAdapter by lazy {
        SearchRecentQueryAdapter()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        _binding = FragmentSearchBinding.bind(requireView())
        with(binding) {
            searchQueryInput.doAfterTextChanged { text ->
                viewModel.onSearchQueryChanged(text?.toString().orEmpty())
            }
            searchRecentQueryList.apply {
                addItemDecoration(
                    MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL)
                )
                adapter = recentQueryAdapter
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

    private fun setUiState(uiState: SearchUiState) {
        setEmptyError(error = uiState.emptyError)
        setRecentQueries(recentQueries = uiState.recentQueries)
    }

    private fun setEmptyError(error: String?) {
        binding.searchErrorView.apply {
            text = error
            isVisible = !error.isNullOrBlank()
        }
    }

    private fun setRecentQueries(recentQueries: List<SearchRecentQuery>) {
        recentQueryAdapter.submitList(recentQueries)
        binding.searchRecentQueryList.isVisible = recentQueries.isNotEmpty()
    }

    override fun onDestroyView() {

        _binding = null
        super.onDestroyView()
    }
}