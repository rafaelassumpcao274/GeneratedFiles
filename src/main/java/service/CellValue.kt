package service

import org.apache.poi.ss.usermodel.Cell
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CellValue(val cell: Cell){
    fun setCellValue( value: Any) {
        // Logic to configure the cell with the value

        // Operations specific to each accepted type
        when (value) {
            is LocalDate -> operationsForLocalDate(value)
            is LocalDateTime -> operationsForLocalDateTime(value)
            is Boolean -> operationsForBoolean(value)
            is Double -> operationsForDouble(value)
            is String -> operationsForString(value)
            is Date -> operationsForDate(value)
            else -> operationsForString(value.toString())
            // Add more conditions as needed for other accepted types
        }
    }

    private fun operationsForLocalDate(value: LocalDate) {
        cell.setCellValue(value)
    }

    private fun operationsForLocalDateTime(value: LocalDateTime) {
        cell.setCellValue(value)
    }

    private fun operationsForBoolean(value: Boolean) {
        cell.setCellValue(value)
    }

    private fun operationsForDouble(value: Double) {
        cell.setCellValue(value)
    }

    private fun operationsForString(value: String) {
        cell.setCellValue(value)
    }

    private fun operationsForDate(value: Date) {
        cell.setCellValue(value)
    }
}

