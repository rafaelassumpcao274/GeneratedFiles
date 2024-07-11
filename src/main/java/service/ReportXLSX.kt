package service


import components.BasicCell
import components.TableXLSX
import enums.Types
import model.CellSheet
import model.ICell
import model.ITable
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import repository.ReportXLSXRepository
import util.NameUtil
import util.SheetUtil
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


open class ReportXLSX() : ReportXLSXRepository {


    protected lateinit var workbook: Workbook;


    private var nameUtil: NameUtil = NameUtil();

    private var nameFile: String = "Report_" + Date().time.toString();

    override fun begin(nameReport: String) {

        workbook = XSSFWorkbook()
        nameUtil = NameUtil()
        nameFile = nameUtil.nameType(nameReport, Types.XLSX)


    }

    override fun begin() {
        workbook = XSSFWorkbook()
    }

    override fun <T> sheet(cellSheet: CellSheet<T>) {
        var sheetUtil: SheetUtil = SheetUtil(workbook.createSheet())
        cellSheet.let { cellSheet ->
            cellSheet.listBasicCell.forEach { cell ->
                if (cell.isMergedCells) {
                    sheetUtil.createMergedColumnRow(cell)
                } else {
                    sheetUtil.createColumnRow(cell)
                }
            }
        }
    }


    override fun sheet(listCell: List<ICell<*>>) {

        var sheetUtil: SheetUtil = SheetUtil(workbook.createSheet())
        listCell.let { cellSheet ->
                for (i in 0 .. cellSheet.size - 1){
                    var cell = cellSheet.get(i)

                    if (cell.isMergedCells) {
                        sheetUtil.createMergedColumnRow(cell)
                    } else {
                        sheetUtil.createColumnRow(cell)
                    }
                }
        }

    }


    override fun sheet(nameSheet: String,listCell: List<ICell<*>>) {

        var sheetUtil: SheetUtil = SheetUtil(workbook.createSheet(nameSheet))
        listCell.let { cellSheet ->
            for (i in 0 .. cellSheet.size - 1){
                var cell = cellSheet.get(i)

                if (cell.isMergedCells) {
                    sheetUtil.createMergedColumnRow(cell)
                } else {
                    sheetUtil.createColumnRow(cell)
                }
            }
        }

    }

    override fun <T> sheet(nameSheet: String, cellSheet: CellSheet<T>) {
        var sheetUtil: SheetUtil = SheetUtil(workbook.createSheet(nameSheet))
        cellSheet.let { cellSheet ->
            cellSheet.listBasicCell.forEach { cell ->
                if (cell.isMergedCells) {
                    sheetUtil.createMergedColumnRow(cell)
                } else {
                    sheetUtil.createColumnRow(cell)
                }
            }

        }
    }


    override fun end() {
        try {
            var fileOut = FileOutputStream(nameFile);
            workbook.write(fileOut)

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun <T> splitListIntoChunks(list: List<T>, chunkSize: Int): Map<Int, List<T>> {
        val chunks = mutableMapOf<Int, MutableList<T>>()

        var chunkIndex = 0

        var listData: MutableList<T> = mutableListOf()


        if (list.size <= chunkSize) {
            chunks.put(0, list.toMutableList())
            return chunks.toMap()
        }

        for (item in list) {

            if (listData.size <= chunkSize) {
                listData.add(item)
            } else {
                chunks.put(chunkIndex, listData)
                listData = mutableListOf(item)

                chunkIndex++
            }

        }

        return chunks.toMap()
    }

}