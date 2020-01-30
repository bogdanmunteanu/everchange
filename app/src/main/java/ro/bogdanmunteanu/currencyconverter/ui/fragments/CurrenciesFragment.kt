package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_currencies.*
import kotlinx.android.synthetic.main.view_loading_list.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity
import ro.bogdanmunteanu.currencyconverter.ui.adapters.BaseCurrencyInputListener
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrenciesAdapter
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyClickListener
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyTypeFactoryImpl
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyDisposable
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel
import java.math.BigDecimal
import javax.inject.Inject


class CurrenciesFragment : DaggerFragment(), CurrencyClickListener, BaseCurrencyInputListener {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var rootView: View
    private lateinit var viewModel: CurrenciesViewModel
    private val adapter =
        CurrenciesAdapter(
            arrayListOf()
            , typeFactory = CurrencyTypeFactoryImpl(), clickListener = this, inputListener = this
        )
    private var disposables = CurrencyDisposable()


    companion object {
        const val TAG: String = "CurrenciesFragment"
    }

    fun newInstance(): CurrenciesFragment {
        return CurrenciesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_currencies, container, false)
        return rootView
    }

    override fun onDestroyView() {
        disposables.dispose()
        super.onDestroyView()
    }

    override fun onItemClick(position: Int, item: CurrencyAbstractModel) {
        adapter.moveItem(position, 0)
        currenciesRecyclerView.recycledViewPool.clear()
        when (item) {
            is BaseCurrencyModel -> {
                viewModel.getLiveCurrencies(
                    item.baseCurrency.isoCode,
                    item.baseCurrency.rate.toString()
                )
            }
            is CurrencyModel -> {
                viewModel.getLiveCurrencies(item.currency.isoCode, item.currency.rate.toString())
            }
        }
        val llm = currenciesRecyclerView.layoutManager as LinearLayoutManager
        llm.scrollToPositionWithOffset(0, 0)
    }

    override fun onTextChanged(text: String, currency: String) {
        viewModel.getLiveCurrencies(currency, text)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, factory).get(CurrenciesViewModel::class.java)
        val layoutManager = LinearLayoutManager(context)
        currenciesRecyclerView.layoutManager = layoutManager
        currenciesRecyclerView.adapter = adapter

        viewModel.fetchState.observe(this, Observer<CurrenciesViewModel.State> {
            when (it) {
                CurrenciesViewModel.State.DONE -> {
                    viewModel.getLiveCurrencies(CurrencyMapper.EUR.title, BigDecimal.ONE.toString())
                }
                CurrenciesViewModel.State.IN_PROGRESS -> {
                    connectionImage.setImageResource(R.drawable.ic_wifi)
                    connectionMessage.setText(R.string.connection_successful)
                    progress.isIndeterminate = true
                    progress.indeterminateDrawable.setColorFilter(
                        resources.getColor(android.R.color.holo_green_dark),
                        PorterDuff.Mode.SRC_IN
                    )
                    settingsButton.visibility = View.GONE
                    networkInfoLayout.visibility = View.GONE // configured, but not
                }
                CurrenciesViewModel.State.ERROR -> {
                    connectionImage.setImageResource(R.drawable.ic_no_wifi)
                    connectionMessage.setText(R.string.service_unavailable_message)
                    progress.isIndeterminate = false
                    progress.progress = 100
                    progress.indeterminateDrawable.setColorFilter(
                        resources.getColor(android.R.color.holo_red_dark),
                        PorterDuff.Mode.SRC_IN
                    )
                    settingsButton.visibility = View.GONE
                    currenciesRecyclerView.visibility = View.INVISIBLE
                    networkInfoLayout.visibility = View.VISIBLE
                    networkInfoLayout.setOnClickListener {
                        viewModel.getLiveCurrencies(
                            getString(R.string.fx_base_currency),
                            BigDecimal.ONE.toString()
                        )
                        loading.visibility = View.VISIBLE
                    }
                }
                CurrenciesViewModel.State.OFFLINE -> {
                    connectionImage.setImageResource(R.drawable.ic_no_wifi)
                    connectionMessage.setText(R.string.no_internet_message)
                    progress.isIndeterminate = true
                    progress.indeterminateDrawable.setColorFilter(
                        resources.getColor(android.R.color.holo_red_dark),
                        PorterDuff.Mode.SRC_IN
                    )
                    settingsButton.visibility = View.VISIBLE
                    currenciesRecyclerView.visibility = View.INVISIBLE
                    settingsButton.setOnClickListener {
                        startActivityForResult(Intent(Settings.ACTION_WIRELESS_SETTINGS), 0)
                    }
                    networkInfoLayout.visibility = View.VISIBLE

                }
                else -> ""
            }
        })

        viewModel.currencies.observe(this, Observer {
            currenciesRecyclerView.visibility = View.VISIBLE
            loading.visibility = View.GONE
            adapter.updateItems(it)
        })

        menuLayout.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //retry api call on result
        viewModel.getLiveCurrencies(CurrencyMapper.EUR.title, BigDecimal.ONE.toString())
    }


}