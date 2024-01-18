package util

import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Workbook

class FontUtil(private var workbook: Workbook) {

    private fun getFontByName( fontName: String): Font {
        val existingFont = workbook.findFont(true, IndexedColors.AUTOMATIC.index, 12, fontName, false, false, Font.SS_NONE, Font.U_NONE)

        return existingFont ?: createFont(fontName)
    }

    private fun createFont( fontName: String): Font {

        val font = workbook.createFont()
        font.fontName = fontName

        return font
    }
}