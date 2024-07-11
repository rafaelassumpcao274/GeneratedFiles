package util

class Utils {

    public val alphabet:List<String> = listOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        );


    fun returnLetterByNumber(column: Int): String {
//        require(column <1) { "Column number must be greater than zero" }

        if (column < alphabet.size) {
            return alphabet.get(column)
        }

        var result = ""
        var n = column

        while (n > 0) {
            val remainder = (n - 1) % 26
            val char = alphabet.get(remainder)
            result = char + result
            n = (n - 1) / 26
        }

        return result
    }
}