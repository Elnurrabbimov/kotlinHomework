package uz.imv.lesson4

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, length = 128) var fullame: String,
    @Column(nullable = false, unique = true) var username: String,
    @Column(nullable = false, length = 64) var password: String,
    @Column(nullable = false, length = 64) var banalce: BigDecimal = 0.toBigDecimal(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)

@Entity
class Category(
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var c_order: Long,
    @Column(nullable = false) var description: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)

@Entity
class Product(
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var count: Long,
    @ManyToOne var category: Category,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)

@Entity
class Transaction(
    @ManyToOne val user: User,
    @Column(nullable = false) var total_amount: BigDecimal,
    var date: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)


@Entity
class TransactionItem(
    @ManyToOne val product: Product,
    @Column(nullable = false) var count: Long,
    @Column(nullable = false) var amount: BigDecimal,
    @Column(nullable = false) var total_amount: BigDecimal,
    @ManyToOne val transaction: Transaction,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)

@Entity
class UserPaymentTransaction(
    @ManyToOne val user: User,
    @Column(nullable = false) var amount: BigDecimal,
    @Column(nullable = false) var date: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)