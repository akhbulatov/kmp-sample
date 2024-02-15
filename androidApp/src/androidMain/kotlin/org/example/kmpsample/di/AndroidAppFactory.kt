package org.example.kmpsample.di

object AndroidAppFactory {

    val appNavigationFactory by lazy {
        AppNavigationFactory()
    }
}