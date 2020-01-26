package ro.bogdanmunteanu.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import ro.bogdanmunteanu.currencyconverter.RxImmediateSchedulerRule
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyDisposable
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel
import java.lang.Exception


@RunWith(MockitoJUnitRunner::class)
class CurrenciesViewModelTest {

    @RelaxedMockK
    lateinit var service : RevolutApiService

    @RelaxedMockK
    lateinit var repository: RevolutServiceRepository

    @RelaxedMockK
    lateinit var stateOberver: Observer<CurrenciesViewModel.State>

    @RelaxedMockK
    lateinit var currenciesObserver: Observer<List<Any>>

    @RelaxedMockK
    lateinit var disposable: CurrencyDisposable

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
        viewModel.fetchState.observeForever(stateOberver)
        every { viewModel.getLiveCurrencies(baseCurrency)} just Runs
        io.mockk.verify(exactly = 1) { viewModel.getLiveCurrencies(baseCurrency) }
        io.mockk.verify{currenciesObserver.onChanged(listOf(CurrencyAbstractModel::class.java))}
    }



}