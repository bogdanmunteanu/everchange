package ro.bogdanmunteanu.currencyconverter.utils

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern


class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {
    lateinit var mPattern: Pattern
    init {
        mPattern =
            Pattern.compile("[0-9]{0,$digitsBeforeZero}+((\\.[0-9]{0,$digitsAfterZero})?)||(\\.)?")
    }
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val matcher: Matcher = mPattern.matcher(dest?.subSequence(0, dstart).toString() + source?.subSequence(start, end).toString() + dest?.subSequence(dend, dest?.length!!).toString())
        if (!matcher.matches())
            return ""
        else
            return null
    }
}