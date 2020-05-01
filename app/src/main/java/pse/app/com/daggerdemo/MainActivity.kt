package pse.app.com.daggerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pse.app.com.daggerdemo.di.components.ActivityComponent
import pse.app.com.daggerdemo.di.components.AppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var car1: Car // Field injection
    @Inject lateinit var car2: Car // Field injection
    lateinit var appComponent: AppComponent
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent = (application as MyApplication).getComponent()

        // To pass value at runtime using PetrolEngineModule
       /* activityComponent = DaggerActivityComponent.builder()
            .petrolEngineModule(PetrolEngineModule(100, 500))
            .build()*/

        // Alterantive way of passing value at runtime using DieselEngineModule
        activityComponent = appComponent.getActivityComponentFactory()
            .create(100, 500)
        activityComponent.inject(this)

        car1.drive()
        car2.drive()
    }
}
