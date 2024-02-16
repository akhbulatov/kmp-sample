package org.example.kmpsample

import Screens
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory
import org.example.kmpsample.auth.AuthFragment
import org.example.kmpsample.repos.ReposFragment
import org.example.kmpsample.search.SearchFragment

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        registerFragment(Screens.Main::class.java, MainFragment::class.java)
        registerFragment(Screens.Repos::class.java, ReposFragment::class.java)
        registerFragment(Screens.Search::class.java, SearchFragment::class.java)
        registerFragment(Screens.Auth::class.java, AuthFragment::class.java)
    }
}
