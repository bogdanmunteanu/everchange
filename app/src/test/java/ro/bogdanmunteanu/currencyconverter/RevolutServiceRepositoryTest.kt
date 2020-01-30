package ro.bogdanmunteanu.currencyconverter

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.reactivex.Single
import ro.bogdanmunteanu.currencyconverter.data.api.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*

import org.mockito.junit.MockitoJUnitRunner
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel


@RunWith(MockitoJUnitRunner::class)
class RevolutServiceRepositoryTest {

    @RelaxedMockK
    lateinit var service : RevolutApiService //Mockito.mock(RevolutApiService::class.java)

    lateinit var  repository :RevolutServiceRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        MockKAnnotations.init(this)
        repository = RevolutServiceRepository(service)
    }

    @Test
    fun repositoryServiceTest(){
        val baseCurrency: String = "EUR"
        val singleResponse = mockk<Single<List<CurrencyAbstractModel>>>(relaxed = true)
        every {repository.getCurrenciesFromEndpoint(baseCurrency)} returns singleResponse//.thenReturn(Single.just(repository.mapCurrencies(mockCurrencies)))
        repository.getCurrenciesFromEndpoint(baseCurrency).test().assertComplete()
        io.mockk.verify(exactly = 1) { service.getCurrencies(baseCurrency) }
    }
}