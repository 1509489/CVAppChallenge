package com.pixelart.cvappchallenge.ui.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelart.cvappchallenge.AppController
import com.pixelart.cvappchallenge.R
import com.pixelart.cvappchallenge.adapter.WorkHistoryAdapter
import com.pixelart.cvappchallenge.di.ActivityComponent
import com.pixelart.cvappchallenge.di.ActivityModule
import com.pixelart.cvappchallenge.model.WorkHistory
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {
    private lateinit var activityComponent: ActivityComponent
    private lateinit var adapter: WorkHistoryAdapter

    @Inject lateinit var presenter: HomeContract.Presenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        injectDependencies()

        presenter.getCV()
        adapter = WorkHistoryAdapter()

        rvWorkHistory.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            addItemDecoration(DividerItemDecoration(this@HomeActivity, LinearLayoutManager.VERTICAL))
            adapter = this@HomeActivity.adapter
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
    
    override fun showCVDetail(name: String, phoneEmail: String, experienceSummary: String,
                              techSkills: String, workHistory: String, education: String, interest: String) {

        tvName.text = name
        tvPhoneEmail.text = phoneEmail
        tvExperience.text = experienceSummary
        tvTechSkills.text = techSkills
        //tvWorkHistory.text = workHistory
        tvEducation.text = education
        tvInterest.text = interest
    }

    override fun showWorkHistory(workHistory: List<WorkHistory>) {
        adapter.submitList(workHistory)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        Log.d("HOME", error)
    }
    
    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun injectDependencies(){
        activityComponent = (application as AppController).applicationComponent
            .newActivityComponent(ActivityModule(this))

        activityComponent.injectHomeScreen(this)
    }
}
