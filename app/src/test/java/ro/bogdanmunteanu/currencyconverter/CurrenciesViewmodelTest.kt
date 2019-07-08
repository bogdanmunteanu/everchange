package ro.bogdanmunteanu.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel
import java.lang.Exception


@RunWith(MockitoJUnitRunner::class)
class CurrenciesViewModelTest {

    @RelaxedMockK
    lateinit var service : RevolutApiService

    @RelaxedMockK
    lateinit var repository: RevolutServiceRepository

    @RelaxedMockK
    lateinit var viewModel: CurrenciesViewModel

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
        MockKAnnotations.init(this)
        repository = RevolutServiceRepository(service)
        viewModel = CurrenciesViewModel(repository)
    }

    @Test
    fun viewModelTest() {
//not working
        val baseCurrency: String = "EUR"
        val stateObserver = mockk<Observer<CurrenciesViewModel.State>>(relaxed = true)

        Mockito.`when`(viewModel.getLiveCurrencies(baseCurrency)).thenAnswer {}
        verify(exactly = 1){ stateObserver.onChanged(CurrenciesViewModel.State.IN_PROGRESS) }


    }
}