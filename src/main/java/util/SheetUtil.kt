package util

import components.Cell
import exceptions.DefaultConfigExcelException
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.util.CellRangeAddress
import service.CellValue
import service.StyleService
import org.apache.poi.ss.usermodel.Cell as CellPoi


class SheetUtil(var sheet: Sheet) {

    private val utils:Utils = Utils()
    public fun createMergedCell(listCellMerge: List<String>) {

        if (listCellMerge.isEmpty() || !listCellMerge.contains("-")) {
            throw DefaultConfigExcelException("Model column 'column/line' not accepted !!")
        }

        var mapColumnsAndRows: MutableMap<String, Int> = mutableMapOf()

        val mapIndexColumns: Map<String, List<String>> = mapOf(
            "initial" to listOf("initialColumn", "initialRow"),
            "final" to listOf("finalColumn", "finalRow")
        )

        for (mapIndexColumn in mapIndexColumns) {
            getColumnAndRows(mapIndexColumn.value, listCellMerge, mapColumnsAndRows)
        }


        val initialColumn = mapColumnsAndRows["initialColumn"] ?: 0
        val initialRow = mapColumnsAndRows["initialRow"] ?: 0
        val finalColumn = mapColumnsAndRows["finalColumn"] ?: 0
        val finalRow = mapColumnsAndRows["finalRow"] ?: 0

        mergedCells(initialColumn, initialRow, finalColumn, finalRow)
    }

    private fun getColumnAndRows(
        index: List<String>,
        listCellMerge: List<String>,
        mapColumnsAndRows: MutableMap<String, Int>
    ) {
        for (chars in listCellMerge) {
            if (chars.length > 2) throw DefaultConfigExcelException("Model column 'column/line' not accepted !!")

            var columnsSplit: List<String> = chars.split('-')

            mapColumnsAndRows[index.get(0)] = transformColunmExcelInNumber(onlyLetter(columnsSplit.get(0)))
            mapColumnsAndRows[index.get(1)] = onlyNumbers(columnsSplit.get(1)).toInt()

        }
    }


    private fun mergedCells(firstRow: Int, lastRow: Int, firstCol: Int, lastCol: Int) {
        val cellRangeAddress = CellRangeAddress(firstRow, lastRow, firstCol, lastCol)
        sheet.addMergedRegion(cellRangeAddress)
    }
//    private  fun ajustarLarguraColuna(sheet:Sheet, column:Int, 1startingFrom:Int):Int {
//        var cellLength:Int = 0
//
//        for (int linha = aPartirDaLinha; linha < sheet.getRows(); linha++) {
//            String conteudoCelula = sheet.getCell(coluna, linha).getContents();
//
//            int fatorPixel = (int) Math.round(getMediaInPixel(conteudoCelula));
//
//            int larguraCelula = (conteudoCelula.length()  + tamCelula);
//
//            if(fatorPixel > 6 && Util.tryParseInt(conteudoCelula) == null && conteudoCelula.length() > 40){
//                larguraCelula += fatorPixel;
//            }
//
//            if (larguraCelula > maiorLargura) {
//                maiorLargura = larguraCelula;
//
//            }
//        }
//
//        return maiorLargura;
//    }

    //    /**
//     *
//     * Verifico se a celula é Mesclada com base na coluna e linha
//     * @param sheet
//     * @param cell
//     * @return
//     */
//    private boolean checkMergedCell(WritableSheet sheet, Cell cell){
//        for (Range mergedCell : sheet.getMergedCells()) {
//            SheetRangeImpl impl = ((SheetRangeImpl) mergedCell);
//            String[] columns = impl.toString().split("-");
//            int colunaMergedIni = transformColunmExcelInNumber(columns[0]);
//            int colunaMergedFinal = transformColunmExcelInNumber(columns[1]);
//
//            int linhaMergedIni = new Integer(onlyNumbers(columns[0])) -1 ;
//            int linhaMergedFinal = new Integer(onlyNumbers(columns[1])) -1;
//
//            if(linhaMergedIni == linhaMergedFinal){
//                if( linhaMergedIni == cell.getRow() &&
//                        (colunaMergedIni >= cell.getColumn() &&  cell.getColumn() <= colunaMergedFinal)){
//                    return true;
//                }
//            }else{
//                if( linhaMergedIni >= cell.getRow() && cell.getRow() <=  linhaMergedFinal &&
//                        (colunaMergedIni >= cell.getColumn() &&  cell.getColumn() <= colunaMergedFinal)){
//                    return true;
//                }
//            }
//
//        }
//        return false;
//    }
//
//
    private fun onlyLetter(input: String): String {
        return input.replace("[^A-Z]", "");
    }

    private fun onlyNumbers(input: String): String {
        return input.replace("[^0-9]", "");
    }

    //    private double getMediaInPixel(String s){
//
//        if (s.length() == 0){
//            return 0;
//        }
//        java.awt.Font fonte = new java.awt.Font("Arial", Font.PLAIN, 12);
//
//        FontMetrics fontMetrics = obterFontMetrics(fonte);
//        int total = 0;
//        for (char c : s.toCharArray()) {
//
//            int largura = obterLarguraDaLetra(fontMetrics, c);
//            total+= largura;
//
//        }
//        return total / s.length();
//
//    }
//
//    private static FontMetrics obterFontMetrics(Font fonte) {
//        // Criar um contexto gráfico temporário para obter as métricas da fonte
//        Graphics graphics = new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
//        FontMetrics fontMetrics = graphics.getFontMetrics(fonte);
//        graphics.dispose();
//        return fontMetrics;
//    }
//
//    private static int obterLarguraDaLetra(FontMetrics fontMetrics, char letra) {
//        // Usar o método charWidth para obter a largura da letra
//        return fontMetrics.charWidth(letra);
//    }

    fun <T> createColumnRow(cell: Cell<T>) {

        var row: Row = createRow(cell)

        var cellPoi: CellPoi = row.createCell(transformColunmExcelInNumber(cell.column))

        verifyTypeAccept(cell, cellPoi);
        cell.style?.let {
            val styleService = StyleService(sheet.workbook)
            var style = styleService.createStyle(it)
            cellPoi.cellStyle = style
        }

        cell.cellSize.let { sheet.autoSizeColumn(cellPoi.columnIndex) }


    }

    private fun <T> verifyTypeAccept(cell: Cell<T>, cellPoi: CellPoi) {
        var cellValue: CellValue = CellValue(cellPoi)
        cell.content?.let { cellValue.setCellValue(it) }

    }

    fun <T> createMergedColumnRow(cell: Cell<T>) {


        var row: Row = createRow(cell)

        var cellPoi: CellPoi = row.createCell(transformColunmExcelInNumber(cell.column))
        verifyTypeAccept(cell, cellPoi);

        cell.mergeCell.let { merge ->

            merge?.let {

                var mergedCell = Cell<T>(it.first, it.second)

                cell.content?.let {mergedCell.content(it)}

                var anotherRow = this.createRow(mergedCell)

                var cellPoiAnother: CellPoi = anotherRow.createCell(transformColunmExcelInNumber(cell.column))

                verifyTypeAccept(cell, cellPoiAnother);

                sheet.addMergedRegion(
                    CellRangeAddress(
                        row.rowNum,
                        anotherRow.rowNum,
                        transformColunmExcelInNumber(cell.column),
                        transformColunmExcelInNumber(mergedCell.column)
                    )
                )
            }
        }

    }

    private fun <T> createRow(cell: Cell<T>): Row {

        if (cell.row != null) {
            return sheet.getRow(cell.row) ?:  sheet.createRow(cell.row)
        }

        return sheet.getRow(sheet.lastRowNum + 1) ?: sheet.createRow(sheet.lastRowNum + 1)
    }

    fun transformColunmExcelInNumber(column: String): Int {
        var alphabet: List<String> = utils.alphabet

        var separateLetter: String = onlyLetter(column);

        if (separateLetter.length < 2) {
            return alphabet.indexOf(separateLetter);
        }

        var result: Int = 0;

        for (char in separateLetter.toCharArray()) {
            result = result * 25 + (alphabet.indexOf((char.toString())) + 1);
        }

        return result;
    }



}