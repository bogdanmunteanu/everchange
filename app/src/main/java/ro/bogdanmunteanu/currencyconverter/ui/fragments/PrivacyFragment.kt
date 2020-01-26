package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.menuLayout
import kotlinx.android.synthetic.main.fragment_privacy.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity

class PrivacyFragment : DaggerFragment() {

    private lateinit var rootView: View

    companion object {
        const val TAG: String = "AboutFragment"
    }

    fun newInstance(): PrivacyFragment {
        return PrivacyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_privacy, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuLayout.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }

        privacyWebView.loadUrl("file:///android_asset/privacy_policy.html")
    }
}