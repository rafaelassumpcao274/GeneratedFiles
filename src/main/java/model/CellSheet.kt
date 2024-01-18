package model

import components.Cell

 class CellSheet<T>{
     var  listCell:List<Cell<*>> = listOfNotNull()


     fun add(cell:Cell<T>){
         listCell.addLast(cell)
     }

     fun addAll(listCell:List<Cell<T>>){
         for (cell in listCell) {
             add(cell)
         }
     }
 }
