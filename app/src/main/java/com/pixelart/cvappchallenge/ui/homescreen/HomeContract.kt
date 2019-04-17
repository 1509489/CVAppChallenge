package com.pixelart.cvappchallenge.ui.homescreen


interface HomeContract {
    interface View{
        fun showCVDetail(name:String, phoneEmail:String, experienceSummary:String, techSkills:String,
                         workHistory:String, education:String, interest:String)
        fun showError(error: String)
        fun showMessage(message: String)
    }
    
    interface Presenter{
        fun getCV()
        fun onStop()
    }
}