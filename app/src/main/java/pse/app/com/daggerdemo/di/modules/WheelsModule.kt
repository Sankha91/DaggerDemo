package pse.app.com.daggerdemo.di.modules

import dagger.Module
import dagger.Provides
import pse.app.com.daggerdemo.Rims
import pse.app.com.daggerdemo.Tyres
import pse.app.com.daggerdemo.Wheels

@Module
class WheelsModule {

    @Provides
    fun provideRims() : Rims {
        return Rims()
    }

    @Provides
    fun provideTyres() : Tyres{
        val tires = Tyres()
        tires.inflate()
        return tires
    }

    @Provides
    fun provideWheels(rims: Rims, tyres: Tyres) : Wheels{
        return Wheels(rims, tyres)
    }
}