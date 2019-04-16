package com.pixelart.cvappchallenge.ui.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pixelart.cvappchallenge.R

class HomeActivity : AppCompatActivity(), HomeContract.View {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    
    override fun showCVDetail(name: String, phoneEmail: String, experienceSummary: String,
                              techSkills: String, workHistory: String, education: String, interest: String) {
        
    }
    
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
    
    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
