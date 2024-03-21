package components

import enums.BorderCell
import enums.TotalType
import model.ITable


class Table<T>: ITable<T> {

     override var cellSize: Int? = null
     override var style: Styles? = Styles().borderCell(BorderCell.ALL)
     override var listContents: List<T>? = null
     override var content:T? = null
     override var column: String = ""
     override var row: Int? = null

     override var nameColumnAndPathValue:Map<String,String?> = hashMapOf()
     override var totalColumns:Map<String,TotalType>? = null

    constructor(initialColumn:String,initialRow:Int?,listContents:List<T>){
        this.column = initialColumn
        this.row = initialRow
        this.listContents = listContents
    }

     fun content(content: List<T>): Table<T> {
         this.listContents = content
         return this
     }


     fun nameColumnAndPathValue(nameColumnAndPathValue:Map<String,String?>): Table<T> {
         this.nameColumnAndPathValue = nameColumnAndPathValue
         return this
     }

 }
