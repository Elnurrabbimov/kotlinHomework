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

interface ProfileRepository : JpaRepository<Profile, Int> {}