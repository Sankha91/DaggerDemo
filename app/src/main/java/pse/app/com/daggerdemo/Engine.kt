package pse.app.com.daggerdemo

import android.util.Log
import pse.app.com.daggerdemo.Car.Companion.TAG
import javax.inject.Inject

interface Engine {
    fun start()
}