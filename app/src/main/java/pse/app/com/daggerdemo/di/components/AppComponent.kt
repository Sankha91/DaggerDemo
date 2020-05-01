package pse.app.com.daggerdemo.di.components

import dagger.Component
import pse.app.com.daggerdemo.Driver
import pse.app.com.daggerdemo.di.modules.DriverModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DriverModule::class))
interface AppComponent {

   // fun getDriver() : Driver // Require only if we are using dependencies instead of @SubComponent
 //   fun getActivityComponentBuilder(): ActivityComponent.Builder // Require if we are using @SubComponent.Builder
    fun getActivityComponentFactory(): ActivityComponent.Factory // Require if we are using @SubComponent.Factory

    /* Since DriverModule is providing Diver Name dynamically */
    @Component.Factory
    interface Factory {
        fun create(driverModule: DriverModule) : AppComponent // Require to pass the module object if the Module class is using any data at runtime.
    }
}