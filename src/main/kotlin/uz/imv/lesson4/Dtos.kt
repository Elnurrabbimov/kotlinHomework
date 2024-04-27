package uz.imv.lesson4

import java.math.BigDecimal
import java.time.LocalDateTime

data class BaseMessage(val code: Int, val message: String)

data class UserCreateDto(
    val username: String, val password: String, val fullname: String, val balance: BigDecimal = 0.toBigDecimal()
) {
    fun toEntity(): User {
        return User(fullname, username, password, balance)
    }
}

data class UserUpdateDto(
    val username: String?, val password: String?, val fullname: String?, val balance: BigDecimal?
)

data class GetUserDto(
    val id: Int, val username: String, val fullname: String, val balance: BigDecimal
) {
    companion object {
        fun toDto(user: User) = user.run { GetUserDto(id!!, username, fullame, banalce) }
    }
}

data class CategoryDto(
    val id: Int, val name: String, val c_order: Long, val description: String
) {
    companion object {
        fun toDto(category: Category) = category.run { CategoryDto(id!!, name, c_order, description) }
    }
}

data class CategoryCreateDto(
    val name: String, val c_order: Long, val description: String
) {
    fun toEntity(): Category {
        return Category(name, c_order, description)
    }
}

data class CategoryUpdateDto(
    val name: String?, val c_order: Long?, val description: String?
)

data class ProductDto(
    val id: Int, val name: String, val count: Long, val category: CategoryDto
) {
    companion object {
        fun toDto(product: Product) = product.run { ProductDto(id!!, name, count, CategoryDto.toDto(category)) }
    }
}

data class ProductCreateDto(
    val name: String, val count: Long, val categoryId: Int
) {
    fun toEntity(category: Category): Product {
        return Product(name, count, category)
    }
}

data class ProductUpdateDto(
    val name: String?, val count: Long?, val categoryId: Int?
)

data class TransactionDto(
    val id: Int, val user: GetUserDto, val totalAmount: BigDecimal, val date: LocalDateTime
) {
    companion object {
        fun toDto(transaction: Transaction) =
            transaction.run { TransactionDto(id!!, GetUserDto.toDto(user), total_amount, date) }
    }
}

data class TransactionCreateDto(
    val userId: Int, val products: List<TransactionItemCreateDto>
) {
    fun toEntity(user: User, totalAmount: BigDecimal, date: LocalDateTime): Transaction {
        return Transaction(user, totalAmount, date)
    }
}

data class TransactionItemDto(
    val id: Int,
    val product: ProductDto,
    val count: Long,
    val amount: BigDecimal,
    val totalAmount: BigDecimal,
    val transaction: TransactionDto
) {
    companion object {
        fun toDto(transactionItem: TransactionItem) = transactionItem.run {
            TransactionItemDto(
                id!!, ProductDto.toDto(product), count, amount, total_amount, TransactionDto.toDto(transaction)
            )
        }
    }
}


data class TransactionItemCreateDto(
    val productId: Int, val count: Long, val amount: BigDecimal
) {
    fun toEntity(product: Product, transaction: Transaction, total_amount: BigDecimal): TransactionItem {
        return TransactionItem(product, count, amount, total_amount, transaction)
    }
}

data class UserPaymentTransactionDto(
    val id: Int, val user: GetUserDto, val amount: BigDecimal
) {
    companion object {
        fun toDto(userPaymentTransaction: UserPaymentTransaction) = userPaymentTransaction.run {
            UserPaymentTransactionDto(id!!, GetUserDto.toDto(user), amount)
        }
    }
}

data class UserPaymentTransactionCreateDto(
    val userId: Int, val amount: BigDecimal
) {
    fun toEntity(user: User): UserPaymentTransaction {
        return UserPaymentTransaction(user, amount)
    }
}

