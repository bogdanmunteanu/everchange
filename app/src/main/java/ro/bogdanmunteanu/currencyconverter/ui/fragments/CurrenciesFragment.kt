package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_currencies.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel
import javax.inject.Inject

class CurrenciesFragment : Fragment(){

    @Inject
    lateinit var factory : ViewModelFactory

    private lateinit var rootView: View
    private lateinit var viewModel: CurrenciesViewModel

    companion object {
        val TAG:String = "CurrenciesFragment"
    }

    fun newInstance(): CurrenciesFragment {
        return CurrenciesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_currencies,container,false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this,factory).get(CurrenciesViewModel::class.java)

        initToolbar()
    }



    private fun initToolbar() {
        fragment_title.text=getString(R.string.app_name)
        fragment_subtitle.text="Live Currencies"

        backIcon.setOnClickListener {
            //show close dialog
        }
    }
}