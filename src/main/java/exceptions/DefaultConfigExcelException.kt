package exceptions

class DefaultConfigExcelException : Exception {
    constructor() : super("Config not accepted.")

    constructor(msg: String) : super(msg)

    constructor(msg: String, cause: Throwable) : super(msg, cause)
}