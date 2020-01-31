package ro.bogdanmunteanu.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

import org.mockito.junit.MockitoJUnitRunner
import ro.bogdanmunteanu.currencyconverter.data.api.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyDisposable
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel
import java.math.BigDecimal


@RunWith(MockitoJUnitRunner::class)
class CurrenciesViewModelTest {

    companion object {
        val baseCurrency: String = "EUR"
        val input: String = BigDecimal.ONE.toString()
    }

    @Mock
    lateinit var service: RevolutApiService

    lateinit var repository: RevolutServiceRepository

    @Mock
    lateinit var stateOberver: Observer<CurrenciesViewModel.State>

    @Mock
    lateinit var currenciesObserver: Observer<List<CurrencyAbstractModel>>

    @Mock
    lateinit var viewModel: CurrenciesViewModel

    @Mock
    lateinit var owner: LifecycleOwner

    @Mock
    lateinit var lifecycle: Lifecycle


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(owner)
        repository = RevolutServiceRepository(service)
        viewModel = CurrenciesViewModel(repository)
        viewModel.currencies.observeForever(currenciesObserver)


    }

    @Test
    fun testFetchData() {
        `when`(repository.getCurrenciesFromEndpoint(baseCurrency, input)).thenReturn(Single.just(
            mock(CurrencyResponse::class.java)))
        viewModel.getLiveCurrencies(baseCurrency, input)
        verify { stateOberver.onChanged(CurrenciesViewModel.State.IN_PROGRESS) }
        verify { currenciesObserver.onChanged(listOf()) }
        assertTrue(viewModel.currencies.hasObservers())
    }

}