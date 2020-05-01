package pse.app.com.daggerdemo

import android.util.Log
import javax.inject.Inject

class Wheels (private var rims : Rims , private var tyres : Tyres) {
    // Suppose, we don't own this class. Let, it belongs to any external library that we can't modify.
  // @Inject constructor(), cannot be used in this case.
    // So in this case, WheelsModule is created.

    fun setup() {
        Log.e(Car.TAG,"Wheels setup done...")
    }
}