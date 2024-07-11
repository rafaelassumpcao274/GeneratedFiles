package components

import model.ICell


class BasicCell<T>(override val column: String, override val row: Int?) : ICell<T> {
    override var style: Styles? = null
    override var isMergedCells: Boolean = false
    override var mergeCell: Pair<String, Int?>? = null
    override var content: T? = null
    override var cellSize: Int? = null

    fun style(style: Styles): BasicCell<T> {
        this.style = style
        return this
    }

    fun cellSize(cellSize: Int): BasicCell<T> {
        this.cellSize = cellSize
        return this
    }

    fun mergeCell(finalColumn: String, finalRow: Int?): BasicCell<T> {
        this.isMergedCells = true
        this.mergeCell = Pair(finalColumn, finalRow)
        return this
    }

    fun content(content: T): BasicCell<T> {
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
