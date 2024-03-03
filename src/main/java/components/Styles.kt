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
     * Generate Cell with font
     */
    fun font(font: Font):Styles {
        this.font = font
        return this
    }
    /**
     * Generate Cell Format currency
     */
    fun currencyFormat(currencyFormat: CurrencyFormat):Styles {
        this.dataFormat = currencyFormat.value
        return this
    }
    /**
     * Generate Cell Format date
     */
    fun dateFormat(dateFormat: DateFormat): Styles {
        this.dataFormat = dateFormat.value
        return this
    }

}