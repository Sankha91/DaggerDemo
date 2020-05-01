package pse.app.com.daggerdemo

import android.util.Log
import pse.app.com.daggerdemo.Car.Companion.TAG
import javax.inject.Inject
import javax.inject.Named

class DieselEngine @Inject constructor(@Named ("hp") var horsepower: Int, @Named ("ec") var engineCapacity : Int) : Engine {
    override fun start() {
        Log.e(TAG, "DieselEngine started. HorsePower : "+horsepower+"\n"+engineCapacity)
    }
}