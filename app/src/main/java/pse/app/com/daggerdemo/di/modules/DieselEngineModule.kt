package pse.app.com.daggerdemo.di.modules

import dagger.Binds
import dagger.Module
import pse.app.com.daggerdemo.DieselEngine
import pse.app.com.daggerdemo.Engine

@Module
abstract class DieselEngineModule {

    /* @Binds is an alternate way for @Provides
    * 1. Must be an abstract method with one argument (the class obj that implements the interface) as parameter
    * 2. The argument class (i.e. DieselEngine) must have an @inject as constructor injection, in order to create the object automatically */
    @Binds
    abstract fun bindDieselEngine(dieselEngine: DieselEngine) : Engine
}