package components


import enums.CurrencyFormat
import enums.DateFormat
import org.apache.commons.codec.binary.Hex
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.xssf.usermodel.XSSFColor
import service.ReportXLSX


class Styles  {


    var backgroundColor:XSSFColor? = null;
    var font:Font? = null;
    var dataFormat:String = ""


    /**
     * Generate a cell for backgroud color in Hexadecimal
     */
    fun backgroundColor(backgroundColor: String):Styles {
        this.backgroundColor = XSSFColor(Hex.decodeHex(backgroundColor), null)
        return this
    }

    /**
     * Generate Cell with font and backgroud color in hexadecimal
     */
    fun font(font: Font):Styles {
        this.font = font
        return this
    }
    /**
     * Generate font backgroud color in hexadecimal and currency format
     */
    fun currencyFormat(currencyFormat: CurrencyFormat):Styles {
        this.dataFormat = currencyFormat.value
        return this
    }

    fun dateFormat(dateFormat: DateFormat): Styles {
        this.dataFormat = dateFormat.value
        return this
    }

}