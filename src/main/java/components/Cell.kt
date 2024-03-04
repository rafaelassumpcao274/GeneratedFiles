package components

import model.ICell


class Cell<T>(override val column: String, override val row: Int?) : ICell<T> {
    override var style: Styles? = null
    var isMergedCells: Boolean = false
    var mergeCell: Pair<String, Int?>? = null
    override var content: T? = null
    override var cellSize: Int? = null

    fun style(style: Styles): Cell<T> {
        this.style = style
        return this
    }

    fun cellSize(cellSize: Int): Cell<T> {
        this.cellSize = cellSize
        return this
    }

    fun mergeCell(finalColumn: String, finalRow: Int?): Cell<T> {
        this.isMergedCells = true
        this.mergeCell = Pair(finalColumn, finalRow)
        return this
    }

    fun content(content: T): Cell<T> {
        this.content = content
        return this
    }

    override fun toString(): String {
        var result = "Column: $column, Row: $row"
        style?.let { result += ", Style: $it" }
        mergeCell?.let { result += ", MergeCell: ${it.first}, ${it.second}" }
        content?.let { result += ", Content: $it" }
        return result
    }
}
