package com.pixelart.cvappchallenge.ui.homescreen

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pixelart.cvappchallenge.model.Cv
import com.pixelart.cvappchallenge.network.NetworkService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test

import org.junit.BeforeClass
import org.mockito.ArgumentMatchers.anyString
import java.util.*
import java.util.concurrent.Executor

class HomePresenterTest {

    private lateinit var presenter: HomePresenter
    private lateinit var cv: Cv

    private val view: HomeContract.View = mock()
    private val networkService: NetworkService = mock()

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupSchedulers(){
            val scheduler = object : Scheduler(){
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        }
    }

    @Before
    fun setUp() {
        presenter = HomePresenter(view, networkService)
        cv = Cv(Collections.emptyList(), "", Collections.emptyList(), Collections.emptyList(), "",
            "", Collections.emptyList(), Collections.emptyList())
    }

    @Test
    fun getCV() {
        whenever(networkService.getCV()).thenReturn(Single.just(cv))
        presenter.getCV()
        verify(view).showCVDetail(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())
    }

    @Test
    fun getCVFailure(){
        whenever(networkService.getCV()).thenReturn(Single.error(Throwable()))
        presenter.getCV()
        Throwable().message?.let { verify(view).showError(it) }
    }
}