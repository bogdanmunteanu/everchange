package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_currencies.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.data.model.Currencies
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel
import javax.inject.Inject

class CurrenciesFragment : DaggerFragment(){

    @Inject
    lateinit var factory : ViewModelFactory

    private lateinit var rootView: View
    private lateinit var viewModel: CurrenciesViewModel
    private val adapter = CurrenciesAdapter(arrayListOf())

    companion object {
        const val TAG:String = "CurrenciesFragment"
    }

    fun newInstance(): CurrenciesFragment {
        return CurrenciesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_currencies,container,false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this,factory).get(CurrenciesViewModel::class.java)

        initToolbar()

        val layoutManager = LinearLayoutManager(context)

        currenciesRecyclerView.layoutManager = layoutManager
        currenciesRecyclerView.adapter = adapter

        viewModel.currencies.observe(this, Observer<List<CurrencyRate>>{
                it -> Log.e("List::",it.toString())
        })

        viewModel.fetchState.observe(this,Observer<CurrenciesViewModel.State>{
            if(it == CurrenciesViewModel.State.IN_PROGRESS){
                viewModel.getLiveCurrencies("USD")
//                loading.visibility = View.VISIBLE
            } else {
//                loading.visibility = View.GONE
            }

            val error = when(it) {
                CurrenciesViewModel.State.ERROR -> "Could not reach Github API"
                CurrenciesViewModel.State.OFFLINE -> "Device is offline.Please connect to the internet and try again"
                else-> ""
            }
//            val errorSnackbar = Snackbar.make(mainLayout, error, Snackbar.LENGTH_INDEFINITE)
//            if (error != "") {
//                errorSnackbar.view.setBackgroundColor(ResourcesCompat.getColor(resources, android.R.color.holo_red_light,null))
//                errorSnackbar.show()
//            } else {
//                errorSnackbar.dismiss()
//            }
        })

        if(viewModel.currencies.value == null) {
            //fetch the data
            viewModel.getLiveCurrencies("USD")
        }else{
            //adapter.setItems(viewModel.repositoryList.value!!)
        }
    }





    private fun initToolbar() {
        fragment_title.text=getString(R.string.app_name)
        fragment_subtitle.text="Live Currencies"

        backIcon.setOnClickListener {
            //show close dialog
        }
    }
}