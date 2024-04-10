package components

import enums.BorderCell
import enums.TotalType
import model.ITable


class Table<T>(initialColumn: String, initialRow: Int?, listContents: List<T>) : ITable<T> {

     override var cellSize: Int? = null
     override var style: Styles? = Styles().borderCell(BorderCell.ALL)
     override var listContents: List<T>? = listContents
     override var content:T? = null
     override var column: String = initialColumn
     override var row: Int? = initialRow

     override var nameColumnAndPathValue:Map<String,String?> = hashMapOf()
     override var totalColumns:Map<String,TotalType>? = null

    fun content(content: List<T>): Table<T> {
         this.listContents = content
         return this
     }


     fun nameColumnAndPathValue(nameColumnAndPathValue:Map<String,String?>): Table<T> {
         this.nameColumnAndPathValue = nameColumnAndPathValue
         return this
     }

 }
