package components

import model.ICell
import model.IFormulaCell

class FormulaCell (override val column: String, override val row: Int?): IFormulaCell {

    override var content: String? = null
    override var cellSize: Int? = null
    override var style: Styles? = Styles()
    override var formula: Boolean = true
    override var isMergedCells: Boolean = false;
    override var mergeCell: Pair<String, Int?>? = null;
    fun style(style: Styles): FormulaCell {
        this.style = style
        return this
    }

    fun cellSize(cellSize: Int): FormulaCell {
        this.cellSize = cellSize
        return this
    }

    fun content(content: String): FormulaCell {
        this.content = content
        return this
    }
    fun formula(formula:Boolean):FormulaCell{
        this.formula = formula
        return this
    }

    override fun toString(): String {
        var result = "Column: $column, Row: $row"
        style?.let { result += ", Style: $it" }
        content?.let { result += ", Content: $it" }
        return result
    }




}