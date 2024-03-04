package components

import model.ICell
import util.CellUtil
import util.SheetUtil
import util.Utils

class TableXLSX<T> (){

    private lateinit var sheetUtil: SheetUtil;
    private  var util: Utils = Utils()
    private  var cellUtils: CellUtil = CellUtil()

    private var listCell:List<Cell<*>> = listOfNotNull()
    fun  createTable(table: Table<T>): List<Cell<*>> {
        require(table.nameColumnAndPathValue.size > 0){ throw RuntimeException("Name column and path not found !!!")}

        val listHeaderCell = createListCell(table, table.nameColumnAndPathValue.size)
        val listCell = mutableListOf<Cell<*>>()

        for ((header, path) in table.nameColumnAndPathValue) {
            val cellHeader = listHeaderCell.find { it.content == null } ?: continue
            cellHeader.content = header

            listCell.add(cellHeader)

            table.listContents?.forEachIndexed { index, clss ->
                val valueType =  cellUtils.findValueWithType<T, Any>(clss, path)
                valueType?.let {
                    listCell.add(
                        Cell<Any>(cellHeader.column, cellHeader.row?.plus(index+1))
                            .content(it.first ?: "")
                    )
                }
            }
        }

        return listCell
    }



    private fun createListCell(cell: ICell<T>, sizeColumnsTable: Int): List<Cell<String>> {

        var columnIndex = util.alphabet.indexOf(cell.column)
        val rowIndex = cell.row ?: 0;

        var listCell:List<Cell<String>> = listOfNotNull()
        for (i in 0..sizeColumnsTable) {
            var colunm:String = util.returnLetterByNumber(columnIndex++)
            var tempCell:Cell<String> = Cell(colunm,rowIndex)

            listCell.addLast(tempCell)
        }

        return listCell
    }


}