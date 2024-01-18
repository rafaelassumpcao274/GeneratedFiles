package util

import exceptions.NameFileException

class StringUtil {

    fun  validIsEmptyOrNull(nameFile: String):Boolean{

        return nameFile.isNullOrEmpty()
    }

    fun verifyCharInString(text:String,char: Char): Boolean{
        return text.contains(char)
    }

    fun validCharWithColumnExcel(listChar:CharArray){

    }
}