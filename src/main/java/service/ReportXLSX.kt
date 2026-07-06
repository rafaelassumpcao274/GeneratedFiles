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


open class ReportXLSX() : ReportXLSXRepository,AutoCloseable {


    protected lateinit var workbook: Workbook;

    private val startTime = System.nanoTime()

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

        listCell
            .filter { cell -> cell.cellSize == null }
            .map { cell -> cell.column }
            .toSet()
            .forEach{
            sheetUtil.cellSize(it)
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


    override fun close() {
        try {
            var fileOut = FileOutputStream(nameFile);
            workbook.write(fileOut)
            val endTime = System.nanoTime()

            println( "It took "+(endTime - startTime) / 1_000_000 +" ms to generate the ${nameFile}")
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


}