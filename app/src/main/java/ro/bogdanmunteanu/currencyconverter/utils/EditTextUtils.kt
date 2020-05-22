package ro.bogdanmunteanu.currencyconverter.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

fun EditText.setTextListener(afterTextChanged: (Pair<Boolean, String>) -> Unit) {
    var inputPasted: Boolean = false
    this.addTextChangedListener {
        object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(inputPasted){
                    afterTextChanged.invoke(Pair(true,s.toString()))
                }else{
                    afterTextChanged.invoke(Pair(false,s.toString()))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                inputPasted = Math.abs(count-before) > 1
            }
        }
    }
}


