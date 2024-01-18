package repository

import components.Cell
import model.CellSheet
import java.util.*


interface ReportXLSXRepository {


    fun begin(nameReport:String)

    fun begin()

    fun <T> sheet(cellSheet: CellSheet<T>)
    fun  sheet(cellSheet: List<Cell<*>>)

    fun <T> sheet(nameSheet:String,cellSheet: CellSheet<T>)
    fun end()

}