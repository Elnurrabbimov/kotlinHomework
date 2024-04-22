package uz.imv.lesson4

data class BaseMessage(val code: Int, val message: String)

data class UserCreateDto(
    val username: String,
    val password: String,
    val role: Role,
    val fullName: String
) {
    fun toEntity(): User {
        return User(fullName, username, password, role)
    }
}


data class UserUpdateDto(
    val username: String?,
    val password: String?,
    val role: Role?,
    val fullName: String?
)

data class GetUserDto(
    val id: Long,
    val username: String,
    val role: Role,
    val fullName: String
) {
    companion object {
//        fun toDto(user: User): GetUserDto {
//            return user.run {
//                GetUserDto(id!!, username, role, fullName)
//            }
//        }

        fun toDto(user: User) = user.run { GetUserDto(id!!, username, role, fullName) }
    }
}

data class ProfileCreateDto(
    val username: String,
    val password: String,
    val role: Role,
    val fullName: String
)

data class ProfileUpdateDto(
    val username: String?,
    val password: String?,
    val role: Role?,
    val fullName: String?
)

data class GetProfileDto(
    val id: Long,
    val username: String,
    val role: Role,
    val fullName: String
)