package com.pixelart.cvappchallenge.ui.homescreen

import com.pixelart.cvappchallenge.model.Cv

interface HomeContract {
    interface View{
        fun showCVDetail(name:String, phoneEmail:String, experienceSummary:String, techSkills:String,
                         workHistory:String, education:String, interest:String)
    }
    
    interface Presenter{
        fun getCV(cv: Cv)
    }
}