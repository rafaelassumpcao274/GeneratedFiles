package util

class CellUtil {

    fun <T, O> findValue(obj: T, property: String): O? {
        val properties = property.split('.')
        var currentValue: Any? = this

        if(properties.isEmpty()){
            return obj as O?;
        }

        for (prop in properties) {
            try {
                val field = currentValue?.javaClass?.getDeclaredField(prop)
                field?.isAccessible = true
                currentValue = field?.get(currentValue)
            } catch (e: NoSuchFieldException) {
                // The property was not found
                return null
            }
        }


        return currentValue as O?
    }


    fun <T, O> findValueWithType(obj: T, property: String?): Pair<O?, Class<out Any>?> {
        if ( property == null || property.isBlank()) {
            return Pair(obj as O?, obj!!::class.java)
        }

        val properties = property.split('.')
        var currentValue: Any? = obj

        for (prop in properties) {
            try {
                val field = currentValue?.javaClass?.getDeclaredField(prop)
                field?.isAccessible = true
                currentValue = field?.get(currentValue)
            } catch (e: NoSuchFieldException) {
                // A propriedade não foi encontrada
                return Pair(null, null)
            }
        }

        if(currentValue != null){

            return Pair(currentValue as O?, currentValue!!::class.java)
        }
        return  Pair(" " as O?,String::class.java)

    }

}