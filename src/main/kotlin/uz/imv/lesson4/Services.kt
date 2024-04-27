package uz.imv.lesson4

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.text.DateFormat
import java.time.LocalDateTime

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
        if (repository.existsByUsername(dto.username)) throw UserAlreadyExistsException(dto.username)

        val user = repository.save(dto.toEntity())
        return GetUserDto.toDto(user)
    }

    override fun updateUser(id: Long, dto: UserUpdateDto): GetUserDto {
        val user = repository.findByIdOrNull(id) ?: throw UserNotFoundException()

        dto.username?.let {
            if (user.username != it && repository.existsByUsername(dto.username)) throw UserAlreadyExistsException(dto.username)
            user.username = it
        }

        dto.password?.let {
            user.password = it
        }

        dto.balance?.let {
            user.banalce = it
        }

        dto.fullname?.let {
            user.fullame = it
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
    fun createCategory(dto: CategoryCreateDto): CategoryDto
    fun updateCategory(id: Long, dto: CategoryUpdateDto): CategoryDto
    fun deleteCategory(id: Long)
    fun getCategoryById(id: Long): CategoryDto
    fun getAllCategories(pageable: Pageable): Page<CategoryDto>
}

@Service
class CategoryServiceImpl(
    private val repository: CategoryRepository
) : CategoryService {
    override fun createCategory(dto: CategoryCreateDto): CategoryDto {
        val category = repository.save(dto.toEntity())
        return CategoryDto.toDto(category)
    }

    override fun updateCategory(id: Long, dto: CategoryUpdateDto): CategoryDto {
        val category = repository.findByIdOrNull(id) ?: throw DataNotFoundException("category")

        dto.c_order?.let {
            category.c_order = it
        }

        dto.description?.let {
            category.description = it
        }

        dto.name?.let {
            category.name = it
        }

        repository.save(category)
        return CategoryDto.toDto(category)
    }

    override fun deleteCategory(id: Long) {
        repository.deleteById(id)
    }

    override fun getCategoryById(id: Long): CategoryDto {
        val category = repository.findByIdOrNull(id) ?: throw DataNotFoundException("category")
        return CategoryDto.toDto(category)
    }

    override fun getAllCategories(pageable: Pageable): Page<CategoryDto> {
        return repository.findAll(pageable).map(CategoryDto.Companion::toDto)
    }
}

interface ProductService {
    fun createProduct(dto: ProductCreateDto): ProductDto
    fun updateProduct(id: Long, dto: ProductUpdateDto): ProductDto
    fun deleteProduct(id: Long)
    fun getProductById(id: Long): ProductDto
    fun getAllProducts(pageable: Pageable): Page<ProductDto>
}

@Service
class ProductServiceImpl(
    private val repository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ProductService {
    override fun createProduct(dto: ProductCreateDto): ProductDto {
        val category =
            categoryRepository.findByIdOrNull((dto.categoryId).toLong()) ?: throw DataNotFoundException("category")
        val product = repository.save(dto.toEntity(category))
        return ProductDto.toDto(product)
    }

    override fun updateProduct(id: Long, dto: ProductUpdateDto): ProductDto {
        val product = repository.findByIdOrNull(id) ?: throw DataNotFoundException("product")

        dto.count?.let {
            product.count = it
        }

        dto.name?.let {
            product.name = it
        }

        dto.categoryId?.let {
            val category = categoryRepository.findByIdOrNull((dto.categoryId).toLong())
            if (category != null)
                product.category = category
        }

        repository.save(product)
        return ProductDto.toDto(product)
    }

    override fun deleteProduct(id: Long) {
        repository.deleteById(id)
    }

    override fun getProductById(id: Long): ProductDto {
        val product = repository.findByIdOrNull(id) ?: throw DataNotFoundException("product")
        return ProductDto.toDto(product)
    }

    override fun getAllProducts(pageable: Pageable): Page<ProductDto> {
        return repository.findAll(pageable).map(ProductDto.Companion::toDto)
    }
}


interface UserPaymentTransactionService {
    fun createUserPaymentTransaction(dto: UserPaymentTransactionCreateDto): UserPaymentTransactionDto
    fun getAllUserPaymentTransactions(pageable: Pageable): Page<UserPaymentTransactionDto>
}

@Service
class UserPaymentTransactionServiceImpl(
    private val repository: UserPaymentTransactionRepository,
    private val userRepository: UserRepository,
) : UserPaymentTransactionService {

    @Transactional
    override fun createUserPaymentTransaction(dto: UserPaymentTransactionCreateDto): UserPaymentTransactionDto {
        val user = userRepository.findByIdOrNull(dto.userId.toLong()) ?: throw DataNotFoundException("user")

        // updates user balance
        dto.let {
            user.banalce += it.amount
        }
        userRepository.save(user)
        // updates user balance

        val userPaymentTransaction = repository.save(dto.toEntity(user))
        return UserPaymentTransactionDto.toDto(userPaymentTransaction)
    }

    override fun getAllUserPaymentTransactions(pageable: Pageable): Page<UserPaymentTransactionDto> {
        return repository.findAll(pageable).map(UserPaymentTransactionDto.Companion::toDto)
    }
}

interface TransactionItemService {
    fun getTransactionByUserId(userId: Long): List<TransactionItemDto>
}

@Service
class TransactionItemServiceImpl(private val repository: TransactionItemRepository) :
    TransactionItemService {

    override fun getTransactionByUserId(userId: Long): List<TransactionItemDto> {
        val data = repository.findAllByIdUserId(userId)
        return data.map { TransactionItemDto.toDto(it) }
    }

}

interface TransactionService {
    fun createProductTransaction(dto: TransactionCreateDto): TransactionDto
}

@Service
class TransactionServiceImpl(
    private val repository: TransactionRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val transactionItemRepository: TransactionItemRepository
) : TransactionService {

    @Transactional
    override fun createProductTransaction(dto: TransactionCreateDto): TransactionDto {
        val user = userRepository.findByIdOrNull(dto.userId.toLong()) ?: throw DataNotFoundException("user")

        var total_amount = 0.0.toBigDecimal()

        var transaction = repository.save(dto.toEntity(user, total_amount, LocalDateTime.now()))

        for (product in dto.products) {
            val productEntity =
                productRepository.findByIdOrNull(product.productId.toLong()) ?: throw DataNotFoundException("product")

            if (productEntity.count - product.count > 0) {
                var total_amount_item = product.amount.multiply(product.count.toBigDecimal())
                total_amount += total_amount_item

                product.count.let {
                    productEntity.count -= it
                }
                productRepository.save(productEntity)

                var transactionItem = TransactionItemCreateDto(product.productId, product.count, product.amount)
                transactionItemRepository.save(transactionItem.toEntity(productEntity, transaction, total_amount_item))
            } else {
                throw NotEnoughProductException(productEntity)
            }
        }


        total_amount.let {
            transaction.total_amount = it
        }

        if (user.banalce - total_amount < 0.toBigDecimal())
            throw NotEnoughMoneyException(user)
        else {
            total_amount.let {
                user.banalce -= it
            }

            userRepository.save(user)
        }


        transaction = repository.save(transaction)

        return TransactionDto.toDto(transaction)
    }

}