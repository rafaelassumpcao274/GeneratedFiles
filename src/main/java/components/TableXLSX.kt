package components

import model.Table
import util.SheetUtil
import util.Utils

class TableXLSX<T>(val table:Table<T>) {

    private lateinit var sheetUtil: SheetUtil
    private  var util: Utils = Utils()

    private var listCell:List<Cell<*>> = listOfNotNull()
    fun createTable(cell: Cell<T>): List<Cell<*>> {

        var listHeaderCell:List<Cell<String>> = createListCell(cell,table.nameColumnAndPathValue.size);

        var listCell:List<Cell<*>> = listOf();

        for (key in table.nameColumnAndPathValue.keys) {
            listHeaderCell.filter { cell -> cell.content == null }
                .first()
                .content = key
        }

        for (cellHeader in listHeaderCell) {
            if (cellHeader.content.isNullOrEmpty()) {
                continue
            }
            val path: String? = table.nameColumnAndPathValue[cellHeader.content]

            listCell.addLast(cellHeader)
            table.list.forEach { clss ->
                val value = path?.let { findValue(clss, it) }

                value?.let {
                    listCell.addLast(
                        Cell<T>(cellHeader.column, cellHeader.row?.plus(listHeaderCell.indexOf(cellHeader)))
                            .content(it)
                    )
                }
            }
        }

       return listCell


    }
    fun <T> findValue(obj: T, property: String): T? {
        val properties = property.split('.')
        var currentValue: Any? = this

        for (prop in properties) {
            try {
                val field = currentValue?.javaClass?.getDeclaredField(prop)
                field?.isAccessible = true
                currentValue = field?.get(currentValue)
            } catch (e: NoSuchFieldException) {
                // The property was not found
                return null
            }
        }

        return currentValue as T?
    }


    private fun createListCell(cell: Cell<T>, sizeColumnsTable: Int): List<Cell<String>> {

        val columnIndex = util.alphabet().indexOf(cell.column)
        val rowIndex = cell.row ?: 0;

        var listCell:List<Cell<String>> = listOfNotNull()
        for (i in 0..sizeColumnsTable) {

            var colunm:String = sheetUtil.returnLetterByNumber(columnIndex+i)
            var tempCell:Cell<String> = Cell(colunm,rowIndex)

            listCell.addLast(tempCell)
        }

        return listCell
    }


}