package model

import components.Cell
import enums.TotalType

 class Table<T>(override var column:String, override var row:Int?): Cell<T>(column, row) {

     /**
      * list content
      */
     lateinit var list: List<T>
     /**
      *  In the first position, provide the column name based on how it will appear in Excel.
      *
      *  In the second position, provide the path based on the class properties to retrieve the corresponding value.
      *  Ex:
      *
      *      class person(var id)
      *
      *      the path = "id"
      *
      *      or
      *
      *      class Company(var id , var person)
      *
      *     but I would like to find the person ID.
      *
      *      the path = "person.id"
      */
     lateinit var nameColumnAndPathValue:Map<String,String>


     var totalColumns:Map<String,TotalType>? = null
 }

