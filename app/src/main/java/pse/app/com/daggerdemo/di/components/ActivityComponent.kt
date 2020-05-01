package pse.app.com.daggerdemo.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import pse.app.com.daggerdemo.MainActivity
import pse.app.com.daggerdemo.di.scopes.ActivityScope
import pse.app.com.daggerdemo.di.modules.DieselEngineModule
import pse.app.com.daggerdemo.di.modules.WheelsModule
import javax.inject.Named

/* @Singleton : Creates only one instance for each Component life cycle
* For different Component it will create different object */

/*@Component (dependencies = arrayOf(AppComponent::class), modules = arrayOf(
    WheelsModule::class, DieselEngineModule::class))
    "dependencies = arrayOf(AppComponent::class)"  are not needed if we are using @SubComponent.
    */
@ActivityScope
@Subcomponent (modules = arrayOf(WheelsModule::class, DieselEngineModule::class))
interface ActivityComponent {
    /* AppComponent : The component whose life time will remain as long as the application is alive */

   /* @Subcomponent.Builder
    interface Builder{
        *//* To pass value at runtime in DieselEngineModule  @Component.Builder with @BindsInstance is used.
        * Alternative way is done using PetrolEngineModule, passing as parameters to module *//*
        @BindsInstance
        fun horsePower(@Named("hp") horsepower : Int): Builder
        *//* @Named identifier is used if we have more than one return type of same type i.e. Int *//*
        @BindsInstance
        fun engineCapacity(@Named("ec")engineCapacity : Int): Builder

        fun build() : ActivityComponent

      //  fun appComponent(appComponent: AppComponent) : Builder // Not require for @SubComponent
    } */

    /* Above Dagger version 2.22 we can use @Subcomponent.Factory instead of @Subcomponent.Builder */

    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance @Named("hp") horsepower : Int,
                   @BindsInstance @Named("ec")engineCapacity : Int): ActivityComponent
    }
      fun inject(mainActivity: MainActivity)
}