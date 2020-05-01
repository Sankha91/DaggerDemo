package pse.app.com.daggerdemo.di.modules

import dagger.Module
import dagger.Provides
import pse.app.com.daggerdemo.Driver
import javax.inject.Singleton


@Module
class DriverModule(private var name:String) {

    @Provides
    fun provideName(): String{
        return name
    }

    @Provides
    @Singleton
    fun provideDriver() : Driver{
        return Driver(name)
    }
}