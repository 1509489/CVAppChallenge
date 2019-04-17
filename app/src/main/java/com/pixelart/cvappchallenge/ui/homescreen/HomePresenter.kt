package com.pixelart.cvappchallenge.ui.homescreen

import com.pixelart.cvappchallenge.model.Cv
import com.pixelart.cvappchallenge.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(private val view: HomeContract.View, private val networkService: NetworkService):
    HomeContract.Presenter {
    
    private val compositeDisposable = CompositeDisposable()
    
    override fun getCV() {
        compositeDisposable.add(
            networkService.getCV()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response -> initCV(response)}, {error -> error?.message?.let { view.showError(it) } })
        )
    }

    private fun initCV(cv: Cv){
        val phoneEmail = "${cv.phoneNumber}, ${cv.email}"
        val experienceSummary = (cv.experienceSummary).joinToString("\n\u2022","\u2022", "", -1, "")
        val techSkills = cv.technicalSkills.joinToString("\n\u2022","\u2022", "", -1, "")
        val education = cv.education.joinToString("\n\u2022", "\u2022", "",-1, "")
        val interest = cv.interests.joinToString("\n\u2022", "\u2022", "",-1, "")

        view.showCVDetail(cv.name, phoneEmail,experienceSummary, techSkills, education, interest)
        view.showWorkHistory(cv.workHistory)
    }
    
    override fun onStop() {
        compositeDisposable.clear()
    }
}