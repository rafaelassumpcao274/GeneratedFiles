package service

import components.Styles
import enums.BorderCell
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Workbook


class StyleService(var workbook: Workbook) {

    companion object {
        // Variável estática para armazenar estilos associados às chaves Styles
        @JvmStatic
        private var stylesUsed: MutableMap<Styles, CellStyle> = mutableMapOf()
    }
    fun createStyle(style: Styles): CellStyle {

        if (stylesUsed.containsKey(style)) {
            return stylesUsed.getValue(style)
        }

        val cellStyle: CellStyle = workbook.createCellStyle()

        style.dataFormat.let { s -> cellStyle.dataFormat = styleFormat(s) }
        style.backgroundColor.let { background -> cellStyle.setFillBackgroundColor(background) }
        style.font.let { font -> cellStyle.setFont(font) }
        style.borderCell.let { styleBord(cellStyle, it) }

        stylesUsed[style] = cellStyle

        return cellStyle

    }

    private fun styleBord(cellStyle: CellStyle, it: BorderCell?) {
        when (it) {
            BorderCell.LEFT -> cellStyle.borderLeft = BorderStyle.THIN
            BorderCell.RIGHT -> cellStyle.borderRight = BorderStyle.THIN
            BorderCell.TOP -> cellStyle.borderTop = BorderStyle.THIN
            BorderCell.BOTTOM -> cellStyle.borderBottom = BorderStyle.THIN
            else -> {
                cellStyle.borderLeft = BorderStyle.THIN
                cellStyle.borderRight = BorderStyle.THIN
                cellStyle.borderTop = BorderStyle.THIN
                cellStyle.borderBottom = BorderStyle.THIN
            }
        }
    }

    private fun styleFormat(format: String): Short {
        val formatCell = workbook.createDataFormat()
        return formatCell.getFormat(format)
    }


}