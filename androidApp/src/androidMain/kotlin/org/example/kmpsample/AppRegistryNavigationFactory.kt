package org.example.kmpsample

import Screens
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory
import org.example.kmpsample.repos.ReposFragment

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        registerFragment(Screens.Main::class.java, MainFragment::class.java)
        registerFragment(Screens.Repos::class.java, ReposFragment::class.java)
    }
}
