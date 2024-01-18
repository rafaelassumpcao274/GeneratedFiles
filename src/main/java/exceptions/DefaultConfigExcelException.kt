package exceptions

class DefaultConfigExcelException : Exception {
    constructor() : super("Confi not accepted.")

    constructor(msg: String) : super(msg)

    constructor(msg: String, cause: Throwable) : super(msg, cause)
}