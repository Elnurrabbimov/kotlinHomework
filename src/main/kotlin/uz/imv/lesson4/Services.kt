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

        dto.balance?.let {
            user.banalce = it
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
        return repository.findAll(pageable).map(GetUserDto.Companion::toDto)
    }
}


interface CategoryService {
    fun createCategory(dto: CategoryDto): CategoryDto
    fun updateCategory(id: Long, dto: CategoryDto): CategoryDto
    fun deleteCategory(id: Long)
    fun getCategoryById(id: Long): CategoryDto
    fun getAllCategories(pageable: Pageable): Page<CategoryDto>
}

@Service
class CategoryServiceImpl(
    private val repository: CategoryRepository
) : CategoryService {
    // Implement methods similar to UserServiceImpl
    override fun createCategory(dto: CategoryDto): CategoryDto {
        TODO("Not yet implemented")
    }

    override fun updateCategory(id: Long, dto: CategoryDto): CategoryDto {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getCategoryById(id: Long): CategoryDto {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(pageable: Pageable): Page<CategoryDto> {
        TODO("Not yet implemented")
    }
}

interface ProductService {
    fun createProduct(dto: ProductDto): ProductDto
    fun updateProduct(id: Long, dto: ProductDto): ProductDto
    fun deleteProduct(id: Long)
    fun getProductById(id: Long): ProductDto
    fun getAllProducts(pageable: Pageable): Page<ProductDto>
}

@Service
class ProductServiceImpl(
    private val repository: ProductRepository
) : ProductService {
    // Implement methods similar to UserServiceImpl
    override fun createProduct(dto: ProductDto): ProductDto {
        TODO("Not yet implemented")
    }

    override fun updateProduct(id: Long, dto: ProductDto): ProductDto {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: Long): ProductDto {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(pageable: Pageable): Page<ProductDto> {
        TODO("Not yet implemented")
    }
}

interface TransactionService {
    fun createTransaction(dto: TransactionDto): TransactionDto
    fun updateTransaction(id: Long, dto: TransactionDto): TransactionDto
    fun deleteTransaction(id: Long)
    fun getTransactionById(id: Long): TransactionDto
    fun getAllTransactions(pageable: Pageable): Page<TransactionDto>
}

@Service
class TransactionServiceImpl(
    private val repository: TransactionRepository
) : TransactionService {
    // Implement methods similar to UserServiceImpl
    override fun createTransaction(dto: TransactionDto): TransactionDto {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(id: Long, dto: TransactionDto): TransactionDto {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(id: Long): TransactionDto {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(pageable: Pageable): Page<TransactionDto> {
        TODO("Not yet implemented")
    }
}

interface TransactionItemService {
    fun createTransactionItem(dto: TransactionItemDto): TransactionItemDto
    fun updateTransactionItem(id: Long, dto: TransactionItemDto): TransactionItemDto
    fun deleteTransactionItem(id: Long)
    fun getTransactionItemById(id: Long): TransactionItemDto
    fun getAllTransactionItems(pageable: Pageable): Page<TransactionItemDto>
}

@Service
class TransactionItemServiceImpl(
    private val repository: TransactionItemRepository
) : TransactionItemService {
    // Implement methods similar to UserServiceImpl
    override fun createTransactionItem(dto: TransactionItemDto): TransactionItemDto {
        TODO("Not yet implemented")
    }

    override fun updateTransactionItem(id: Long, dto: TransactionItemDto): TransactionItemDto {
        TODO("Not yet implemented")
    }

    override fun deleteTransactionItem(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getTransactionItemById(id: Long): TransactionItemDto {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionItems(pageable: Pageable): Page<TransactionItemDto> {
        TODO("Not yet implemented")
    }
}

interface UserPaymentTransactionService {
    fun createUserPaymentTransaction(dto: UserPaymentTransactionDto): UserPaymentTransactionDto
    fun updateUserPaymentTransaction(id: Long, dto: UserPaymentTransactionDto): UserPaymentTransactionDto
    fun deleteUserPaymentTransaction(id: Long)
    fun getUserPaymentTransactionById(id: Long): UserPaymentTransactionDto
    fun getAllUserPaymentTransactions(pageable: Pageable): Page<UserPaymentTransactionDto>
}

@Service
class UserPaymentTransactionServiceImpl(
    private val repository: UserPaymentTransactionRepository
) : UserPaymentTransactionService {
    // Implement methods similar to UserServiceImpl
    override fun createUserPaymentTransaction(dto: UserPaymentTransactionDto): UserPaymentTransactionDto {
        TODO("Not yet implemented")
    }

    override fun updateUserPaymentTransaction(id: Long, dto: UserPaymentTransactionDto): UserPaymentTransactionDto {
        TODO("Not yet implemented")
    }

    override fun deleteUserPaymentTransaction(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getUserPaymentTransactionById(id: Long): UserPaymentTransactionDto {
        TODO("Not yet implemented")
    }

    override fun getAllUserPaymentTransactions(pageable: Pageable): Page<UserPaymentTransactionDto> {
        TODO("Not yet implemented")
    }
}