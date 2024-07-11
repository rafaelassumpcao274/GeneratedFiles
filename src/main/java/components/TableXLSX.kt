package components

import model.ICell
import util.CellUtil
import util.Utils

class TableXLSX<T>() {


    private var util: Utils = Utils()
    private var cellUtils: CellUtil = CellUtil()

    fun createTable(table: Table<T>): List<ICell<*>> {
        require(table.nameColumnAndPathValue.size > 0) { throw RuntimeException("Name column and path not found !!!") }

        val listHeaderCell = createListCell(table, table.nameColumnAndPathValue.size)
        var columnActual = table.column
        val row = table.row ?: 1
        val listBasicCell = mutableListOf<ICell<*>>()

        for ((header, path) in table.nameColumnAndPathValue) {
            val cellHeader = createCellHeader(columnActual,row,header)
            listBasicCell.add(cellHeader)

            table.listContents?.forEachIndexed { index, clss ->
                val valueType = cellUtils.findValueWithType<T, Any>(clss, path)
                valueType.let {

                    var rowActual = row + index
                    if(index < 1){
                        rowActual = row + index + 1
                    }

                    listBasicCell.add(
                        BasicCell<Any>(cellHeader.column, rowActual)
                            .content(it.first ?: "")
                            .style(table.style ?: Styles())
                    )
                }
            }

            if(table.totalColumns != null && table.totalColumns!!.containsKey(header)){

                var last = listBasicCell.last()
                var formula:String = (table.totalColumns!!.get(header)?.name ?: "SUM") +"("+cellHeader.column+(cellHeader.row?.plus(1))+":"+ last.column+last.row+")"
                listBasicCell.add(FormulaCell(cellHeader.column,last.row?.plus(1)).content(formula))
            }
            columnActual = nextColumn(columnActual)
        }

        return listBasicCell
    }

    private fun createCellHeader(columnActual: String, row: Int, header: String): ICell<*> {
        return  BasicCell<String>(columnActual,row)
            .content(header)
            .style(Styles())
    }

    private fun nextColumn(columnActual: String): String{
        return util.returnLetterByNumber(util.alphabet.indexOf(columnActual) + 1)
    }


    private fun createListCell(cell: ICell<T>, sizeColumnsTable: Int): List<BasicCell<String>> {

        var columnIndex = util.alphabet.indexOf(cell.column)
        val rowIndex = cell.row ?: 1;

        var listBasicCell: ArrayList<BasicCell<String>> = ArrayList()
        for (i in 0..sizeColumnsTable) {
            var colunm: String = util.returnLetterByNumber(columnIndex++)
            var tempBasicCell: BasicCell<String> = BasicCell<String>(colunm, rowIndex)
                .content(cell.content.toString())
                .style(cell.style ?: Styles())

            listBasicCell.add(tempBasicCell)
        }

        return listBasicCell
    }


}