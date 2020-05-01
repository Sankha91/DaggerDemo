package pse.app.com.daggerdemo.di.modules

import dagger.Module
import dagger.Provides
import pse.app.com.daggerdemo.Engine
import pse.app.com.daggerdemo.PetrolEngine
import javax.inject.Named


@Module
class PetrolEngineModule(@Named ("hp") var horsepower : Int, @Named ("ec") var engineCapacity : Int)
/* Module parameters are used to dynamically pass values at runtime*/ {

    /* @Named("Key") is used as an identifier, if the return type of two variables in @Provides method is same */
    @Named ("hp")
    @Provides
    fun provideHorsePower() : Int {
        return horsepower
    }

    @Named ("ec")
    @Provides
    fun provideEngineCapacity():Int {
        return engineCapacity
    }

    @Provides
    fun providePetrolEngine(petrolEngine: PetrolEngine) : Engine{
        return petrolEngine
    }
}