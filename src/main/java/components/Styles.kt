package components


import enums.Alignment
import enums.BorderCell
import enums.CurrencyFormat
import enums.DateFormat
import model.IStyle
import org.apache.commons.codec.binary.Hex
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.xssf.usermodel.XSSFColor
import service.ReportXLSX


class Styles : IStyle {


    override var backgroundColor:XSSFColor? = null;
    override var textColor:XSSFColor? = null;
    override var font:Font? = null;
    override var dataFormat:String = ""
    override var borderCell: BorderCell? = null
    override var alignmentContent: Alignment = Alignment.LEFT;

    constructor()


    /**
     * Generate a cell for backgroud color in Hexadecimal
     */
    fun backgroundColor(backgroundColor: String):Styles {
        this.backgroundColor = XSSFColor(Hex.decodeHex(backgroundColor), null)
        return this
    }

    /**
     * Generate a cell for text color in Hexadecimal
     */
    fun textColor(textcolor: String):Styles {
        this.textColor = XSSFColor(Hex.decodeHex(textcolor), null)
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

    /**
     * Generate a Border Format
     */
    fun borderCell(borderCell: BorderCell): Styles {
        this.borderCell = borderCell;
        return this
    }


    /**
     * this  Alignment Content
     */
    fun alignmentContent(alignment: Alignment): Styles {
        this.alignmentContent = alignment;
        return this
    }



}