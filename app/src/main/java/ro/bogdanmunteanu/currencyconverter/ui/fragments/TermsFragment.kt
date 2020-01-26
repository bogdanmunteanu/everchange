package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_terms.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity

class TermsFragment : DaggerFragment() {

    private lateinit var rootView: View

    companion object {
        const val TAG: String = "AboutFragment"
    }

    fun newInstance(): TermsFragment {
        return TermsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_terms, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuLayout.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }
        termsWebView.loadUrl("file:///android_asset/terms_and_conditions.html")
    }
}