package components

interface ICell<T> {

     val column: String
     val row: Int?
     var content: T?
     var cellSize:Int?
     var style: Styles?

}