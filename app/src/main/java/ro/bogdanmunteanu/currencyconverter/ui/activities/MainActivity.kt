package ro.bogdanmunteanu.currencyconverter.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.ui.UiUtils
import ro.bogdanmunteanu.currencyconverter.ui.fragments.CurrenciesFragment

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout.setViewScale(Gravity.START, 0.9f)
        drawerLayout.setViewElevation(Gravity.START, 20f)

        leftSideView.setNavigationItemSelectedListener(this)
        showCurrenciesFragment()

        btnConverter.setOnClickListener {
            showCurrenciesFragment()
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
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
}
