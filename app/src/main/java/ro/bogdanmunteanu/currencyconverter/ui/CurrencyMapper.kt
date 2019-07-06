package ro.bogdanmunteanu.currencyconverter.ui

enum class CurrencyMapper(val title:String,val subtitle:String,val flagURL:String)
{
    EUR("EUR","Euro","https://en.wikipedia.org/wiki/Euro#/media/File:Flag_of_Europe.svg"),
    AUD("AUD","Australian dollar","https://en.wikipedia.org/wiki/Australia#/media/File:Flag_of_Australia_(converted).svg"),
    BGN("BGN","Bulgarian lev","https://en.wikipedia.org/wiki/Bulgaria#/media/File:Flag_of_Bulgaria.svg"),
    BRL("BRL","Brazilian real","https://en.wikipedia.org/wiki/Brazil#/media/File:Flag_of_Brazil.svg"),
    CAD("CAD","Canadian dollar","https://en.wikipedia.org/wiki/Canada#/media/File:Flag_of_Canada_(Pantone).svg"),
    CHF("CHF","Swiss franc","https://en.wikipedia.org/wiki/Switzerland#/media/File:Flag_of_Switzerland_(Pantone).svg"),
    CNY("CNY","Chinese yuan","https://en.wikipedia.org/wiki/China#/media/File:Flag_of_the_People's_Republic_of_China.svg"),
    CZK("CZK","Czech koruna","https://en.wikipedia.org/wiki/Czech_Republic#/media/File:Flag_of_the_Czech_Republic.svg"),
    DKK("DKK","Danish krone","https://en.wikipedia.org/wiki/Denmark#/media/File:Flag_of_Denmark.svg"),
    GBP("GBP","British pound","https://en.wikipedia.org/wiki/United_Kingdom#/media/File:Flag_of_the_United_Kingdom.svg"),
    HKD("HKD","Hong Kong dollar","https://en.wikipedia.org/wiki/United_Kingdom#/media/File:Flag_of_the_United_Kingdom.svg"),
    HRK("HRK","Croatian kuna","https://en.wikipedia.org/wiki/Croatia#/media/File:Flag_of_Croatia.svg"),
    HUF("HUF","Hungarian forint","https://en.wikipedia.org/wiki/Hungary#/media/File:Flag_of_Hungary.svg"),
    IDR("IDR","Indonesian rupiah","https://en.wikipedia.org/wiki/Indonesia#/media/File:Flag_of_Indonesia.svg"),
    ILS("ILS","Israeli new shekel","https://en.wikipedia.org/wiki/Israel#/media/File:Flag_of_Israel.svg"),
    INR("INR","Indian rupee","https://en.wikipedia.org/wiki/India#/media/File:Flag_of_India.svg"),
    ISK("ISK","Icelandic krona","https://en.wikipedia.org/wiki/Iceland#/media/File:Flag_of_Iceland.svg"),
    JPY("JPY","Japanese yen","https://en.wikipedia.org/wiki/Japan#/media/File:Flag_of_Japan(bordered).svg"),
    KRW("KRW","South Korean won","https://en.wikipedia.org/wiki/South_Korea#/media/File:Flag_of_South_Korea.svg"),
    MXN("MXN","Mexican peso","https://en.wikipedia.org/wiki/Mexico#/media/File:Flag_of_Mexico.svg"),
    MYR("MYR","Malaysian ringgit","https://en.wikipedia.org/wiki/Malaysia#/media/File:Flag_of_Malaysia.svg"),
    NOK("NOK","Norwegian krone","https://en.wikipedia.org/wiki/Norway#/media/File:Flag_of_Norway.svg"),
    NZD("NZD","New Zeeland dollar","https://en.wikipedia.org/wiki/New_Zealand#/media/File:Flag_of_New_Zealand.svg"),
    PHP("PHP","Phillippine peso","https://en.wikipedia.org/wiki/Philippines#/media/File:Flag_of_the_Philippines.svg"),
    PLN("PLN","Polish zloty","https://en.wikipedia.org/wiki/Poland#/media/File:Flag_of_Poland.svg"),
    RON("RON","Romanian leu","https://en.wikipedia.org/wiki/Romania#/media/File:Flag_of_Romania.svg"),
    RUB("RUB","Russian ruble","https://en.wikipedia.org/wiki/Russia#/media/File:Flag_of_Russia.svg"),
    SEK("SEK","Swedish krona","https://en.wikipedia.org/wiki/Sweden#/media/File:Flag_of_Sweden.svg"),
    SGD("SGD","Singapore dollar","https://en.wikipedia.org/wiki/Singapore#/media/File:Flag_of_Singapore.svg"),
    THB("THB","Thai baht","https://en.wikipedia.org/wiki/Thailand#/media/File:Flag_of_Thailand.svg"),
    TRY("TRY","Turkish lira","https://en.wikipedia.org/wiki/Turkey#/media/File:Flag_of_Turkey.svg"),
    USD("USD","U.S. Dollar","https://en.wikipedia.org/wiki/United_States#/media/File:Flag_of_the_United_States.svg"),
    ZAR("ZAR","South African rand","https://en.wikipedia.org/wiki/South_Africa#/media/File:Flag_of_South_Africa.svg");

    companion object {
        fun fromTitle(title:String): CurrencyMapper = values().find { value -> value.title==title } ?: EUR
    }












}