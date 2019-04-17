package com.pixelart.cvappchallenge.ui.homescreen

import com.pixelart.cvappchallenge.model.WorkHistory


interface HomeContract {
    interface View{
        fun showCVDetail(name:String, phoneEmail:String, experienceSummary:String, techSkills:String,
                         education:String, interest:String)
        fun showWorkHistory(workHistory: List<WorkHistory>)
        fun showError(error: String)
        fun showMessage(message: String)
    }
    
    interface Presenter{
        fun getCV()
        fun onStop()
    }
}