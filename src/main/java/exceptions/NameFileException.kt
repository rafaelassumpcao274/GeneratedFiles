package exceptions

class NameFileException : Exception {
    constructor() : super("File Name not accepted.")

    constructor(msg: String) : super(msg)

    constructor(msg: String, cause: Throwable) : super(msg, cause)
}
