package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_currencies.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRow
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
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
        viewModel = ViewModelProviders.of(this, factory).get(CurrenciesViewModel::class.java)

        initToolbar()

        val layoutManager = LinearLayoutManager(context)
        currenciesRecyclerView.layoutManager = layoutManager
        currenciesRecyclerView.adapter = adapter
        adapter.context = context
        adapter.onItemClick = {
            adapter.moveItem(it.first,0)
            viewModel.getLiveCurrencies((it.second as CurrencyRow).isoCode)
            val llm = currenciesRecyclerView.layoutManager as LinearLayoutManager
            llm.scrollToPositionWithOffset(0, 0)
        }

        viewModel.fetchState.observe(this,Observer<CurrenciesViewModel.State>{
            when(it) {
                CurrenciesViewModel.State.DONE->{
                    viewModel.getLiveCurrencies(CurrencyMapper.EUR.title)
                }
                CurrenciesViewModel.State.IN_PROGRESS->{
                    connectionImage.setImageResource(R.drawable.ic_wifi)
                    connectionMessage.setText(R.string.connection_successful)
                    progress.isIndeterminate = true
                    progress.indeterminateDrawable.setColorFilter(resources.getColor(android.R.color.holo_green_dark),PorterDuff.Mode.SRC_IN)
                    settingsButton.visibility=View.GONE
                }
                CurrenciesViewModel.State.ERROR -> {
                    connectionImage.setImageResource(R.drawable.ic_no_wifi)
                    connectionMessage.setText(R.string.service_unavailable_message)
                    progress.isIndeterminate = false
                    progress.progress=50
                    progress.indeterminateDrawable.setColorFilter(resources.getColor(android.R.color.holo_red_dark),PorterDuff.Mode.SRC_IN)
                    settingsButton.visibility=View.GONE
                }
                CurrenciesViewModel.State.OFFLINE -> {
                    connectionImage.setImageResource(R.drawable.ic_no_wifi)
                    connectionMessage.setText(R.string.no_internet_message)
                    progress.isIndeterminate = true
                    progress.indeterminateDrawable.setColorFilter(resources.getColor(android.R.color.holo_red_dark),PorterDuff.Mode.SRC_IN)
                    settingsButton.visibility=View.VISIBLE
                    settingsButton.setOnClickListener {
                        startActivityForResult(Intent(Settings.ACTION_WIRELESS_SETTINGS),0)
                    }
                }
                else-> ""
            }
        })

        viewModel.currencies.observe(this, Observer {
           Log.e(TAG,it.toString())
            adapter.updateItems(it)
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //retry api call on result
        viewModel.getLiveCurrencies(CurrencyMapper.EUR.title)

    }

    private fun initToolbar() {
        fragment_title.text=getString(R.string.app_name)
        fragment_subtitle.text=getString(R.string.app_subtitle)
    }
}