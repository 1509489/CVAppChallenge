package com.pixelart.cvappchallenge.ui.homescreen

import com.pixelart.cvappchallenge.model.Cv
import com.pixelart.cvappchallenge.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View, private val networkService: NetworkService):
    HomeContract.Presenter {
    
    private val compositeDisposable = CompositeDisposable()
    
    override fun getCV(cv: Cv) {
        compositeDisposable.add(
            networkService.getCV()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe({response -> initCV(response)}, {error -> error?.message?.let { view.showError(it) } })
        )
    }
    
    private fun initCV(cv: Cv){
        val phoneEmail = "${cv.phoneNumber}, ${cv.email}"
        val experienceSummary = (cv.experienceSummary).joinToString("\n\u2022","\u2022", "", -1, "")
        val techSkills = cv.technicalSkills.joinToString("\n\u2022","\u2022", "", -1, "")
        val education = cv.education.joinToString("\n\u2022", "\u2022", "",-1, "")
        val interest = cv.interests.joinToString("\n\u2022", "\u2022", "",-1, "")
        val workList: List<String> = ArrayList()
        
        for (work in 0 until cv.workHistory.size){
            val companyName = cv.workHistory[work].companyName
            val role = cv.workHistory[work].role
            val fromTo = "${cv.workHistory[work].from} - ${cv.workHistory[work].to}"
            val appDescription = cv.workHistory[work].appDescription
            val mainResponsibilities = (cv.workHistory[work].mainResponsibilities).joinToString("\n\u2022", "\u2022", "",-1, "")
            
            val output = "$companyName \n\n$role \n\n$fromTo \n\n$appDescription \n\n$mainResponsibilities"
            (workList as ArrayList).add(output)
        }
        
        val workHistory = workList.joinToString("\n\n", "", "",-1, "")
        
        view.showCVDetail(cv.name, phoneEmail,experienceSummary, techSkills, workHistory, education, interest)
    }
    
    override fun onStop() {
        compositeDisposable.clear()
    }
}