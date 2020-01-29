package ro.bogdanmunteanu.currencyconverter.ui.activities

import android.R
import android.view.ViewGroup
import android.widget.FrameLayout
import android.os.Bundle
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity


@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            this.id = R.id.content
        }
        setContentView(content)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.content, fragment, "TEST")
            .commit()
    }
}
