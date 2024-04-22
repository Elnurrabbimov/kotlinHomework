package uz.imv.lesson4

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, length = 128) var fullName: String,//default 255
    @Column(nullable = false, unique = true) var username: String,
    @Column(nullable = false, length = 64) var password: String,
    @Enumerated(EnumType.STRING) @Column(length = 32, nullable = false) var role: Role,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)

@Entity
class Profile(
    val image: String,
    @OneToOne val user: User,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null
)