package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_about.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity


class AboutFragment : DaggerFragment() {

    private lateinit var rootView: View

    companion object {
        const val TAG: String = "AboutFragment"
    }

    fun newInstance(): AboutFragment {
        return AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_about, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuLayout.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }
        fragmentTitle.setText(prepareAppTitle(), TextView.BufferType.SPANNABLE)
        ivLinkedin.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://profile/bogdan-munteanu-08031066/"))
            val packageManager = context!!.packageManager
            val list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (list.isEmpty()) {
                intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.linkedin.com/profile/view?id=bogdan-munteanu-08031066/")
                )
            }
            startActivity(intent)
        }

        wvAbout.loadUrl("file:///android_asset/about.html")
    }

    private fun prepareAppTitle(): SpannableStringBuilder {
        val builder = SpannableStringBuilder()

        context?.let { ctx->
            val str1 = SpannableString(getString(R.string.everchange_title_start))
            str1.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(ctx, R.color.orange)),
                0,
                str1.length,
                0
            )
            builder.append(str1)

            val str2 = SpannableString(getString(R.string.everchange_title_end))
            str2.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(ctx, R.color.white)),
                0,
                str2.length,
                0
            )
            builder.append(str2)
        }
        return builder
    }
}