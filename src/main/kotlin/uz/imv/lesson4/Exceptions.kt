package uz.imv.lesson4

class UserAlreadyExistsException(val userName: String) : RuntimeException()

class UserNotFoundException : RuntimeException()

class DataNotFoundException(val table: String) : RuntimeException()