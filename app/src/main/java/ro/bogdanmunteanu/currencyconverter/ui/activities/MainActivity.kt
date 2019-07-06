package ro.bogdanmunteanu.currencyconverter.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dagger.android.support.DaggerAppCompatActivity
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.ui.fragments.CurrenciesFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCurrenciesFragment()
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
}
