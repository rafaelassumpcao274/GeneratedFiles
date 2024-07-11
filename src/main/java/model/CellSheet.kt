package model

import components.BasicCell

 class CellSheet<T>{
     var  listBasicCell:List<BasicCell<*>> = listOfNotNull()


     fun add(basicCell:BasicCell<T>){
         listBasicCell.addLast(basicCell)
     }

     fun addAll(listBasicCell:List<BasicCell<T>>){
         for (cell in listBasicCell) {
             add(cell)
         }
     }
 }
