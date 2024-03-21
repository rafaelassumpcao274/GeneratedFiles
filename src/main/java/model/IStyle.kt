package model

import enums.Alignment
import enums.BorderCell
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.xssf.usermodel.XSSFColor


interface IStyle {

    var backgroundColor: XSSFColor?
    var textColor: XSSFColor?
    var font: Font?
    var dataFormat:String
    var borderCell:BorderCell?
    var alignmentContent: Alignment
}