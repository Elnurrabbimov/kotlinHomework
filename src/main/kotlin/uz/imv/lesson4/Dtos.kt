package uz.imv.lesson4

import java.math.BigDecimal
import java.text.DateFormat

data class BaseMessage(val code: Int, val message: String)

data class UserCreateDto(
    val username: String,
    val password: String,
    val fullName: String,
    val balance: String
) {
    fun toEntity(): User {
        return User(fullName, username, password, balance)
    }
}

data class UserUpdateDto(
    val username: String?,
    val password: String?,
    val fullName: String?,
    val balance: String?
)

data class GetUserDto(
    val id: Int,
    val username: String,
    val fullName: String,
    val balance: String
) {
    companion object {
        fun toDto(user: User) = user.run { GetUserDto(id!!, username, fullName, banalce) }
    }
}

data class CategoryDto(
    val id: Int,
    val name: String,
    val order: Long,
    val description: String
) {
    companion object {
        fun toDto(category: Category) = category.run { CategoryDto(id!!, name, c_order, description) }
    }
}

data class ProductDto(
    val id: Int,
    val name: String,
    val count: Long,
    val category: CategoryDto
) {
    companion object {
        fun toDto(product: Product) = product.run { ProductDto(id!!, name, count, CategoryDto.toDto(category)) }
    }
}

data class TransactionDto(
    val id: Int,
    val user: GetUserDto,
    val totalAmount: BigDecimal,
    val date: DateFormat
) {
    companion object {
        fun toDto(transaction: Transaction) =
            transaction.run { TransactionDto(id!!, GetUserDto.toDto(user), total_amount, date) }
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
                id!!,
                ProductDto.toDto(product),
                count,
                amount,
                total_amount,
                TransactionDto.toDto(transaction)
            )
        }
    }
}

data class UserPaymentTransactionDto(
    val id: Int,
    val user: GetUserDto,
    val amount: BigDecimal,
    val date: DateFormat
) {
    companion object {
        fun toDto(userPaymentTransaction: UserPaymentTransaction) = userPaymentTransaction.run {
            UserPaymentTransactionDto(
                id!!,
                GetUserDto.toDto(user),
                amount,
                date
            )
        }
    }
}

