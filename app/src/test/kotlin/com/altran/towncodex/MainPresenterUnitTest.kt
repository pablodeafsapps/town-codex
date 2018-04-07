package com.altran.towncodex

import com.altran.towncodex.main.MainContract
import com.altran.towncodex.main.MainPresenter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

import javax.inject.Inject

class MainPresenterUnitTest {

    @Inject
    lateinit var interactor: MainContract.Interactor

    @Before
    fun setUp() {
        // This is needed for Dagger 2 injections only on tests
        BaseApplication.INSTANCE = BaseApplication()
//        DaggerAppComponent.builder().application(BaseApplication.INSTANCE).build().inject(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun whenQueryResultIsFailureToastIsShown() {
        //given
        val view = Mockito.mock(MainContract.View::class.java)
        val presenter = MainPresenter()

        val target = spy(presenter)
        // when
        target.loadAndFilterList("asfasdfasdf")
        // then
        verify(view).showNoDataWarning(true)
    }
}