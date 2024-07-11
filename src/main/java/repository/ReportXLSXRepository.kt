package repository

import components.BasicCell
import model.CellSheet
import model.ICell


interface ReportXLSXRepository {


    fun begin(nameReport:String)

    fun begin()

    fun <T> sheet(cellSheet: CellSheet<T>)
    fun sheet(nameSheet: String,listCell: List<ICell<*>>)
    fun  sheet(listCell: List<ICell<*>>)

    fun <T> sheet(nameSheet:String,cellSheet: CellSheet<T>)
    fun end()

}