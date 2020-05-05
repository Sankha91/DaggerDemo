package com.sankha.daggerdemo2

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sankha.daggerdemo2.dashboard.viewmodel.DashboardViewModel
import com.sankha.daggerdemo2.dashboard.viewmodel.DashboardViewModelFactory
import com.sankha.daggerdemo2.db.WordEntity
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.PostLoginComponent

import kotlinx.android.synthetic.main.activity_view.*
import javax.inject.Inject

class ViewActivity : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog
    @Inject
    lateinit var builder : AlertDialog.Builder
    @Inject
    lateinit var dashboardViewModelFactory: DashboardViewModelFactory

    lateinit var viewListAdapter: ViewListAdapter
    lateinit var dashboardViewModel: DashboardViewModel
    lateinit var appComponent: AppComponent
    lateinit var postLoginComponent: PostLoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        appComponent = (application as MyApplication).getAppComponent()
        postLoginComponent = appComponent.getPostLoginComponent()
            .activityContext(this)
            .build()
        postLoginComponent.injectViewActivity(this)

        dashboardViewModel = ViewModelProviders.of(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)

        builder.setTitle("Delete!").setMessage("Are you sure?").setCancelable(false)
        builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dashboardViewModel.deleteAll()
                dialog?.cancel()
            }

        })
        builder.setNegativeButton("No", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.cancel()
            }

        })
        alertDialog = builder.create()
        dashboardViewModel.fetchAll()

        dashboardViewModel.fetchLiveData().observe(this, Observer {
            if (it.isNotEmpty()){
                tvNodata.visibility = View.GONE
                recyclerVw.visibility = View.VISIBLE
                tvDeleteAll.visibility = View.VISIBLE
                with(recyclerVw){
                    viewListAdapter = ViewListAdapter(it as ArrayList<WordEntity>, this@ViewActivity)
                    adapter = viewListAdapter
                }
            }
            else{
                tvNodata.visibility = View.VISIBLE
                recyclerVw.visibility = View.GONE
                tvDeleteAll.visibility = View.GONE
            }
        })

        tvDeleteAll.setOnClickListener {
            alertDialog.show()
        }

        dashboardViewModel.getDeleteLiveData().observe(this, Observer {
            if (it) Toast.makeText(this@ViewActivity, "Deleted Successfully !!", Toast.LENGTH_LONG).show()
            dashboardViewModel.fetchAll()
        })

        // For Swiping of Recyclerview Items
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.e(MyApplication.TAG, "onSwiped..."+viewHolder.adapterPosition)
                dashboardViewModel.delete(viewListAdapter.getItemAtPosition(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(recyclerVw)
    }
}
