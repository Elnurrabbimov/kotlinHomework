package uz.imv.lesson4

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handlerFunctionAdapter(e: UserAlreadyExistsException): ResponseEntity<*> {
        return ResponseEntity.badRequest()
            .body(BaseMessage(0, "User already exists  wiht ${e.userName}"))
    }

    @ExceptionHandler(DataNotFoundException::class)
    fun handlerDataNotFound(e: DataNotFoundException): ResponseEntity<*> {
        return ResponseEntity.badRequest()
            .body(BaseMessage(0, "Data not found in defined table : ${e.table}"))
    }

    @ExceptionHandler(NotEnoughProductException::class)
    fun handlerNotEnoughProduct(e: NotEnoughProductException): ResponseEntity<*> {
        return ResponseEntity.badRequest()
            .body(
                BaseMessage(
                    0,
                    "Not enough product in store: Product -  ${e.product.name}, Count - ${e.product.count}"
                )
            )
    }

    @ExceptionHandler(NotEnoughMoneyException::class)
    fun handlerDataNotFound(e: NotEnoughMoneyException): ResponseEntity<*> {
        return ResponseEntity.badRequest()
            .body(BaseMessage(0, "Not enough money in balance. User - : ${e.user.username}"))
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

@RestController
@RequestMapping("api/v1/category")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping
    fun createCategory(@RequestBody dto: CategoryCreateDto) = categoryService.createCategory(dto)

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: Long) = categoryService.getCategoryById(id)

    @PutMapping("{key}")
    fun updateUser(@PathVariable("key") id: Long, @RequestBody dto: CategoryUpdateDto) =
        categoryService.updateCategory(id, dto)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long) = categoryService.deleteCategory(id)

    @GetMapping
    fun getAllUsers(pageable: Pageable) = categoryService.getAllCategories(pageable)
}

@RestController
@RequestMapping("api/v1/product")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun createProduct(@RequestBody dto: ProductCreateDto) = productService.createProduct(dto)

    @PutMapping("{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody dto: ProductUpdateDto) =
        productService.updateProduct(id, dto)

    @DeleteMapping("{id}")
    fun deleteProduct(@PathVariable id: Long) = productService.deleteProduct(id)

    @GetMapping("{id}")
    fun getProductById(@PathVariable id: Long) = productService.getProductById(id)

    @GetMapping
    fun getAllProducts(pageable: Pageable) = productService.getAllProducts(pageable)
}

@RestController
@RequestMapping("api/v1/user-payment-transaction")
class UserPaymentTransactionController(private val userPaymentTransactionService: UserPaymentTransactionService) {

    @PostMapping
    fun createUserPaymentTransaction(@RequestBody dto: UserPaymentTransactionCreateDto) =
        userPaymentTransactionService.createUserPaymentTransaction(dto)

    @GetMapping
    fun getAllUserPaymentTransactions(pageable: Pageable) =
        userPaymentTransactionService.getAllUserPaymentTransactions(pageable)
}


@RestController
@RequestMapping("api/v1/market")
class MarketController(
    private val transactionService: TransactionService,
    private val transactionItemService: TransactionItemService,
) {

    @PostMapping
    fun buyProduct(@RequestBody dto: TransactionCreateDto): TransactionDto =
        transactionService.createProductTransaction(dto)

    @GetMapping("transaction-history/{userId}")
    fun getTransactionHistory(@PathVariable userId: Long) = transactionItemService.getTransactionByUserId(userId)
}