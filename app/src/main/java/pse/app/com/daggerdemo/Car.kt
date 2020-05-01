package pse.app.com.daggerdemo

import android.util.Log
import pse.app.com.daggerdemo.di.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class Car @Inject constructor(private var wheels : Wheels, var driver: Driver) /* Constructor injection */ {

    @Inject lateinit var engine: Engine // Field injection
    companion object{
        val TAG = "Car"
    }

    fun drive(){
        engine.start()
        Log.e(TAG,driver.toString() +" ... "+driver.name+" is driving..."+this)
    }

    @Inject // Method injection
    fun setupWheels(){
        wheels.setup()
    }
}