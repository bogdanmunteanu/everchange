package ro.bogdanmunteanu.currencyconverter


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
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
        rule().activity.setFragment(CurrenciesFragment().newInstance())
    }

    @Test
    fun visibleFields() {
        onView(withId(R.id.currenciesLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        onView(withId(R.id.currenciesRecyclerView)).check(matches(not(isDisplayed())))
    }
}