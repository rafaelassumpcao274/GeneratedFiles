package util

import enums.Types
import exceptions.NameFileException

class NameUtil {


    val dot:Char = '.'

    var stringUtil: StringUtil = StringUtil()
    fun nameType(fileName:String,type:Types) :String{

        if(stringUtil.validIsEmptyOrNull(fileName)){
            throw NameFileException()
        }

        if(type == null){
            throw NameFileException("Type not accepted !!!")
        }

        return formatNameFile(formatLengthName(fileName),type)

    }

    private fun formatNameFile(fileName:String,type:Types): String{
        return fileName+dot+type.value
    }



    private fun formatLengthName(fileName: String): String {
        if (fileName.length > 30) {
            return fileName.substring(0, 27) + "..."
        }
        return fileName
    }
}