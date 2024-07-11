package util
class DynamicArrayWrapper(private val array: Array<Any?>) {

    @Throws(NoSuchFieldException::class)
    fun get(propertyName: String): Any? {
        val index = getIndexFromProperty(propertyName)
        return array[index]
    }

    @Throws(NoSuchFieldException::class)
    fun set(propertyName: String, value: Any?) {
        val index = getIndexFromProperty(propertyName)
        array[index] = value
    }

    private fun getIndexFromProperty(propertyName: String): Int {
        return propertyName.toIntOrNull()  ?: throw NoSuchFieldException("Invalid property name: $propertyName")


    }
}