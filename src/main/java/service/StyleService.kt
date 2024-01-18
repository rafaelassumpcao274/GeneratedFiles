package service

import components.Styles
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Workbook


class StyleService(var workbook: Workbook ) {


    fun createStyle(style: Styles) : CellStyle {

        var cellStyle:CellStyle = workbook.createCellStyle()

        style.dataFormat.let { s ->  cellStyle.dataFormat = styleFormat(s)}
        style.backgroundColor.let { background -> cellStyle.setFillBackgroundColor(background) }
        style.font.let { font -> cellStyle.setFont(font) }

        return  cellStyle

    }

    private fun styleFormat(format:String): Short{
        val formatCell = workbook.createDataFormat()
        return formatCell.getFormat(format)
    }



}