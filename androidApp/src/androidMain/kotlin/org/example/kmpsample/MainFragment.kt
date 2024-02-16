package org.example.kmpsample

import Screens
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import me.aartikov.alligator.Navigator
import org.example.kmpsample.databinding.FragmentMainBinding
import org.example.kmpsample.di.AndroidAppFactory

class MainFragment : Fragment(R.layout.fragment_main) {

    private val navigator: Navigator =
        AndroidAppFactory.appNavigationFactory.navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        with(binding) {
            mainNetworkButton.setOnClickListener {
                navigator.goForward(Screens.Repos)
            }
            mainDatabaseButton.setOnClickListener {
                navigator.goForward(Screens.Search)
            }
            mainPreferencesButton.setOnClickListener {
                navigator.goForward(Screens.Auth)
            }
        }
    }
}