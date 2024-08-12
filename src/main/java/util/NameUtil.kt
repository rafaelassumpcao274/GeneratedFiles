package util

import enums.Types
import exceptions.NameFileException

class NameUtil {


    private val dot:Char = '.'

    private var stringUtil: StringUtil = StringUtil()
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
        if(fileName.contains(".")){
            val dotIndex = fileName.lastIndexOf('.')
            if (dotIndex != -1) {
                return (fileName.substring(0, dotIndex) )+dot+type.value
            }
        }
        return fileName+dot+type.value
    }



    private fun formatLengthName(fileName: String): String {
        if (fileName.length > 30) {
            return fileName.substring(0, 27) + "..."
        }
        return fileName
    }
}