package pse.app.com.daggerdemo

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class PetrolEngine @Inject constructor(@Named("hp") var horsepower: Int, @Named ("ec") var engineCapacity : Int)  : Engine {
    override fun start() {
        Log.e(Car.TAG, "PetrolEngine started. HorsePower : "+horsepower+"\n"+engineCapacity)
    }
}