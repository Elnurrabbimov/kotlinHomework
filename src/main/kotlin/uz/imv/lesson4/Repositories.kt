package uz.imv.lesson4

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.PathVariable

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsername(username: String): Boolean
}

interface CategoryRepository : JpaRepository<Category, Long> {
    // Add any additional query methods if needed
}

interface ProductRepository : JpaRepository<Product, Long> {
    // Add any additional query methods if needed
}

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findAllByUserId(userId: Long): List<Transaction>
}

interface TransactionItemRepository : JpaRepository<TransactionItem, Long> {

    @Query(
        "SELECT ti FROM TransactionItem ti " +
                "JOIN ti.transaction t " +
                "WHERE t.user.id = :userId"
    )
    fun findAllByIdUserId(userId: Long): List<TransactionItem>

    abstract fun save(toEntity: Transaction): Transaction
}

interface UserPaymentTransactionRepository : JpaRepository<UserPaymentTransaction, Long> {
    // Add any additional query methods if needed
}