package pse.app.com.daggerdemo

import android.app.Application
import pse.app.com.daggerdemo.di.components.AppComponent
import pse.app.com.daggerdemo.di.components.DaggerAppComponent
import pse.app.com.daggerdemo.di.modules.DriverModule

/* This class is created, to avoid creation of Dagger component again and again on activity created.
* If needed we can create Component specific to activity with custom scopes */
class MyApplication : Application() {

    lateinit private var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(DriverModule("The GOD"))
    }

    fun getComponent() : AppComponent {
        return appComponent
    }
}