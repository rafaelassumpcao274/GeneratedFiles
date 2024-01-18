package service


import components.Cell
import enums.Types
import model.CellSheet
import org.apache.poi.ss.usermodel.Sheet
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


    private  var nameUtil: NameUtil = NameUtil();

    private var nameFile :String = "Report_" + Date().time.toString();

    override fun begin(nameReport: String) {

        workbook =  XSSFWorkbook()
        nameUtil = NameUtil()
        nameFile = nameUtil.nameType(nameReport,Types.XLSX)


    }

    override fun begin() {
        workbook =  XSSFWorkbook()
    }

    override fun <T> sheet(cellSheet: CellSheet<T>) {
        var sheetUtil:SheetUtil = SheetUtil(workbook.createSheet())
        cellSheet.let { cellSheet ->
            cellSheet.listCell.forEach { cell ->
                if(cell.isMergedCells){
                    sheetUtil.createMergedColumnRow(cell)
                }else{
                    sheetUtil.createColumnRow(cell)
                }
            }

        }


    }

    override fun  sheet(cellSheet: List<Cell<*>>) {
        var sheetUtil:SheetUtil = SheetUtil(workbook.createSheet())
        cellSheet.let { cellSheet ->
            cellSheet.forEach { cell ->
                if(cell.isMergedCells){
                    sheetUtil.createMergedColumnRow(cell)
                }else{
                    sheetUtil.createColumnRow(cell)
                }
            }
        }




    }

    override fun <T> sheet(nameSheet: String, cellSheet: CellSheet<T>) {
        var sheetUtil:SheetUtil = SheetUtil(workbook.createSheet(nameSheet))
        cellSheet.let { cellSheet ->
            cellSheet.listCell.forEach { cell ->
                if(cell.isMergedCells){
                    sheetUtil.createMergedColumnRow(cell)
                }else{
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


}