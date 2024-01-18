package util

import java.text.NumberFormat
import java.util.*

class NumberFormatUtil {

    fun convertLocal(number:Double,locale: Locale):String{
        val customFormat = NumberFormat.getInstance(locale)

        return customFormat.format(number)
    }
}