package ro.bogdanmunteanu.currencyconverter


import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ro.bogdanmunteanu.currencyconverter.data.api.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository


@RunWith(MockitoJUnitRunner::class)
class RevolutServiceRepositoryTest {

    @RelaxedMockK
    lateinit var service : RevolutApiService

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
        val singleResponse = mockk<Single<CurrencyResponse>>(relaxed = true)
        every {repository.getCurrenciesFromApi(baseCurrency)} returns singleResponse//.thenReturn(Single.just(repository.mapCurrencies(mockCurrencies)))
        repository.getCurrenciesFromApi(baseCurrency).test().assertComplete()
        io.mockk.verify(exactly = 1) { service.getCurrencies(baseCurrency) }
    }
}