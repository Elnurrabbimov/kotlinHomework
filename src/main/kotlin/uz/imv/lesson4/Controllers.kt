package uz.imv.lesson4

import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handlerFunctionAdapter(e: UserAlreadyExistsException): ResponseEntity<*> {
        return ResponseEntity.badRequest()
            .body(BaseMessage(0, "User already exists  wiht ${e.userName}"))
    }


}


@RestController
@RequestMapping("api/v1/user")
class UserController(private val userService: UserService) {
    @PostMapping
    fun createUser(@RequestBody dto: UserCreateDto) = userService.createUser(dto)

    @PutMapping("{key}")
    fun updateUser(@PathVariable("key") id: Long, @RequestBody dto: UserUpdateDto) = userService.updateUser(id, dto)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: Long) = userService.getUserById(id)

    @GetMapping
    fun getAllUsers(pageable: Pageable) = userService.getAllUsers(pageable)
}