package ro.bogdanmunteanu.currencyconverter

import android.service.autofill.Validators.not
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ro.bogdanmunteanu.currencyconverter.ui.activities.SingleFragmentActivity
import ro.bogdanmunteanu.currencyconverter.ui.fragments.CurrenciesFragment

@RunWith(AndroidJUnit4::class)
class CurrenciesFragmentTest {

    val testActivityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    fun rule() = testActivityRule

    @Before
    fun setUp() {
        rule().activity.setFragment(CurrenciesFragment())
    }

    @Test
    fun visibleFields() {
        Espresso.onView(withId(R.id.networkInfoLayout)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.connectionImage)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.connectionMessage)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.networkInfoLayout)).isGone()


    }

    fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

    fun ViewInteraction.isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

    fun ViewInteraction.isInvisible() = getViewAssertion(ViewMatchers.Visibility.INVISIBLE)


    private fun getViewAssertion(visibility: ViewMatchers.Visibility): ViewAssertion? {
        return ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
    }
}