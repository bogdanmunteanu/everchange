package ro.bogdanmunteanu.currencyconverter.ui.activities

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.ui.UiUtils
import ro.bogdanmunteanu.currencyconverter.ui.fragments.AboutFragment
import ro.bogdanmunteanu.currencyconverter.ui.fragments.CurrenciesFragment
import ro.bogdanmunteanu.currencyconverter.ui.fragments.PrivacyFragment
import ro.bogdanmunteanu.currencyconverter.ui.fragments.TermsFragment
import java.lang.StringBuilder

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAppTitle.setText(prepareAppTitle(),TextView.BufferType.SPANNABLE)
        drawerLayout.setViewScale(Gravity.START, 0.9f)
        drawerLayout.setViewElevation(Gravity.START, 20f)

        leftSideView.setNavigationItemSelectedListener(this)
        showCurrenciesFragment()

        btnConverter.setOnClickListener {
            showCurrenciesFragment()
        }

        btnAbout.setOnClickListener {
            showAboutFragment()
        }
        btnTermsAndConditions.setOnClickListener {
            showTermsFragment()
        }

        btnPrivacyPolicy.setOnClickListener {
            showPrivacyFragment()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true //this needed implementation , not used because of custom menu
    }

    private fun showCurrenciesFragment() {
        if (supportFragmentManager.findFragmentByTag(CurrenciesFragment.TAG) == null) {
            addFragment(CurrenciesFragment().newInstance() as Fragment, R.id.frame)
        } else {
            replaceFragment(supportFragmentManager.findFragmentByTag(CurrenciesFragment.TAG) as Fragment, R.id.frame)
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun showAboutFragment() {
        if (supportFragmentManager.findFragmentByTag(AboutFragment.TAG) == null) {
            addFragment(AboutFragment().newInstance() as Fragment, R.id.frame)
        } else {
            replaceFragment(supportFragmentManager.findFragmentByTag(AboutFragment.TAG) as Fragment, R.id.frame)
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun showTermsFragment() {
        if (supportFragmentManager.findFragmentByTag(TermsFragment.TAG) == null) {
            addFragment(TermsFragment().newInstance() as Fragment, R.id.frame)
        } else {
            replaceFragment(supportFragmentManager.findFragmentByTag(TermsFragment.TAG) as Fragment, R.id.frame)
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun showPrivacyFragment() {
        if (supportFragmentManager.findFragmentByTag(PrivacyFragment.TAG) == null) {
            addFragment(PrivacyFragment().newInstance() as Fragment, R.id.frame)
        } else {
            replaceFragment(supportFragmentManager.findFragmentByTag(PrivacyFragment.TAG) as Fragment, R.id.frame)
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment).addToBackStack(fragment.tag).setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left) }
    }
    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId,fragment).setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left) }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.()-> FragmentTransaction)
    {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


    fun openDrawer() {
        UiUtils.hideKeyboard(this)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun prepareAppTitle():SpannableStringBuilder{
        val builder = SpannableStringBuilder()

        val str1 = SpannableString("ever")
        str1.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange)), 0, str1.length, 0)
        builder.append(str1)

        val str2 = SpannableString("change")
        str2.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.white)), 0, str2.length, 0)
        builder.append(str2)
        return builder
    }
}
