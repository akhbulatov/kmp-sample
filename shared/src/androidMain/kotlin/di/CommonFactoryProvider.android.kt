package di

import org.example.kmpsample.KmpSampleApp

actual object CommonFactoryProvider {

    actual val commonFactory: CommonFactory
        get() = KmpSampleApp.commonFactory
}