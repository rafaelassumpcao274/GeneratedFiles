package components

import enums.TotalType

interface ITable<T>:ICell<T> {

     var listContents:List<T>?
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
     var nameColumnAndPathValue:Map<String,String?>
     var totalColumns:Map<String, TotalType>?


}