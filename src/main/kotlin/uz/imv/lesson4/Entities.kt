package uz.imv.lesson4

import jakarta.persistence.*
import java.math.BigDecimal
import java.text.DateFormat

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, length = 128) var fullName: String,
    @Column(nullable = false, unique = true) var username: String,
    @Column(nullable = false, length = 64) var password: String,
    @Column(nullable = false, length = 64) var banalce: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
)

@Entity
class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var c_order: Long,
    @Column(nullable = false) var description: String,
)

@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var count: Long,
    @OneToOne val category: Category,
)

@Entity
class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    @OneToOne val user: User,
    @Column(nullable = false) var total_amount: BigDecimal,
    var date: DateFormat,
)


@Entity
class TransactionItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    @OneToOne val product: Product,
    @Column(nullable = false) var count: Long,
    @Column(nullable = false) var amount: BigDecimal,
    @Column(nullable = false) var total_amount: BigDecimal,
    @OneToOne val transaction: Transaction,
)

@Entity
class UserPaymentTransaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    @OneToOne val user: User,
    @Column(nullable = false) var amount: BigDecimal,
    @Column(nullable = false) var date: DateFormat,
)