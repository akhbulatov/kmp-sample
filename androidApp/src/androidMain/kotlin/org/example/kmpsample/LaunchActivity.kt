package org.example.kmpsample

import Screens
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.navigationfactories.NavigationFactory
import org.example.kmpsample.di.AndroidAppFactory

class LaunchActivity : AppCompatActivity(R.layout.activity_launch) {

    private val navigator: Navigator =
        AndroidAppFactory.appNavigationFactory.navigator
    private val navigationFactory: NavigationFactory =
        AndroidAppFactory.appNavigationFactory.navigationFactory
    private val navigationContextBinder: NavigationContextBinder =
        AndroidAppFactory.appNavigationFactory.navigationContextBinder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigator.goBack()
            }
        })
        if (savedInstanceState == null) {
            navigator.reset(Screens.Main)
        }
    }

    override fun onResume() {
        super.onResume()
        bindNavigationContext()
    }

    private fun bindNavigationContext() {
        val navigationContext = NavigationContext.Builder(this, navigationFactory)
            .fragmentNavigation(supportFragmentManager, R.id.launch_fragment_container)
            .build()
        navigationContextBinder.bind(navigationContext)
    }

    override fun onPause() {
        navigationContextBinder.unbind(this)
        super.onPause()
    }
}