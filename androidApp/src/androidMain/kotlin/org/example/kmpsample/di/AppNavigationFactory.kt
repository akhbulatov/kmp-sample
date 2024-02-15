package org.example.kmpsample.di

import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.NavigationFactory
import org.example.kmpsample.AppRegistryNavigationFactory

class AppNavigationFactory {

    private val androidNavigator by lazy {
        AndroidNavigator(AppRegistryNavigationFactory())
    }

    val navigator: Navigator get() = androidNavigator
    val navigationFactory: NavigationFactory get() = androidNavigator.navigationFactory
    val navigationContextBinder: NavigationContextBinder get() = androidNavigator
    val screenResolver: ScreenResolver get() = androidNavigator.screenResolver
}