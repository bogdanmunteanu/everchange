# Currency Converter

## Details about technologies and libraries used 

The entire project is written in Kotlin.

## Built With

* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - ViewModel,Livedata
* [Retrofit 2.3](https://square.github.io/retrofit/) - For calling API
* [Dagger 2](https://github.com/google/dagger) - Depedency management 
* [RxJava2/RxKotlin2](https://github.com/ReactiveX/RxKotlin) - For mananging API calls and data display using a reactive way
* [Glide](https://github.com/bumptech/glide) - For Image loading from Country Flags API

### Details about how the app works 

All components and activities/fragments are injected by Dagger at runtime , keeping an overall low-memory and CPU consumption on the traget device.
The app uses MVVM architecture with a single activity and a single fragment. It follows the guidelines set by Google regarding the MVVM architecture

```
Every flow in the application should have a single activity with multiple fragments for that flow.
```
