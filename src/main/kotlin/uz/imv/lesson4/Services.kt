package uz.imv.lesson4

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface UserService {
    fun createUser(dto: UserCreateDto): GetUserDto
    fun updateUser(id: Long, dto: UserUpdateDto): GetUserDto
    fun deleteUser(id: Long)
    fun getUserById(id: Long): GetUserDto
    fun getAllUsers(pageable: Pageable): Page<GetUserDto>
}

interface ProfileService {
    fun createProfile(dto: ProfileCreateDto): GetProfileDto
    fun updateProfile(id: Int, dto: ProfileUpdateDto): GetProfileDto
    fun deleteProfile(id: Int)
    fun getProfileById(id: Int): GetProfileDto
    fun getAllProfiles(pageable: Pageable): Page<GetProfileDto>
}

@Service
class UserServiceImpl(
    private val repository: UserRepository
) : UserService {
    override fun createUser(dto: UserCreateDto): GetUserDto {
        if (repository.existsByUsername(dto.username))
            throw UserAlreadyExistsException(dto.username)
        val user = repository.save(dto.toEntity())
        return GetUserDto.toDto(user)
    }

    override fun updateUser(id: Long, dto: UserUpdateDto): GetUserDto {
        val user = repository.findByIdOrNull(id) ?: throw UserNotFoundException()

        dto.username?.let {
            if (user.username != it && repository.existsByUsername(dto.username))
                throw UserAlreadyExistsException(dto.username)
            user.username = it
        }

        dto.password?.let {
            user.password = it
        }

        dto.role?.let {
            user.role = it
        }

        dto.fullName?.let {
            user.fullName = it
        }

        repository.save(user)
        return GetUserDto.toDto(user)
    }

    override fun deleteUser(id: Long) {
        repository.deleteById(id)
    }

    override fun getUserById(id: Long): GetUserDto {
        val user = repository.findByIdOrNull(id) ?: throw UserNotFoundException()
        return GetUserDto.toDto(user)
    }

    override fun getAllUsers(pageable: Pageable): Page<GetUserDto> {
//        return response.map { GetUserDto.toDto(it) }
        return repository.findAll(pageable).map(GetUserDto.Companion::toDto)
    }
}

