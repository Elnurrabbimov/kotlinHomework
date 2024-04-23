package uz.imv.lesson4

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): User? //JPA method

    @Query("select u from User u where u.username = ?1")//JPQL - JAVA PERSISTENCE QUERY LANGUAGE
    fun customQuery(username: String): User?

    @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)//NATIVE QUERY
    fun customNativeQuery(username: String): User?
}

interface CategoryRepository : JpaRepository<Category, Long> {
    // Add any additional query methods if needed
}

interface ProductRepository : JpaRepository<Product, Long> {
    // Add any additional query methods if needed
}

interface TransactionRepository : JpaRepository<Transaction, Long> {
    // Add any additional query methods if needed
}

interface TransactionItemRepository : JpaRepository<TransactionItem, Long> {
    // Add any additional query methods if needed
}

interface UserPaymentTransactionRepository : JpaRepository<UserPaymentTransaction, Long> {
    // Add any additional query methods if needed
}