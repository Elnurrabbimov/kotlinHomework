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
    fun createCategory(@RequestBody dto: CategoryDto): ResponseEntity<CategoryDto> {
        val createdCategory = categoryService.createCategory(dto)
        return ResponseEntity.ok(createdCategory)
    }

    @PutMapping("{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody dto: CategoryDto): ResponseEntity<CategoryDto> {
        val updatedCategory = categoryService.updateCategory(id, dto)
        return ResponseEntity.ok(updatedCategory)
    }

    @DeleteMapping("{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Unit> {
        categoryService.deleteCategory(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryDto> {
        val category = categoryService.getCategoryById(id)
        return ResponseEntity.ok(category)
    }

    @GetMapping
    fun getAllCategories(pageable: Pageable): ResponseEntity<Page<CategoryDto>> {
        val categories = categoryService.getAllCategories(pageable)
        return ResponseEntity.ok(categories)
    }
}


@RestController
@RequestMapping("api/v1/product")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun createProduct(@RequestBody dto: ProductDto): ResponseEntity<ProductDto> {
        val createdProduct = productService.createProduct(dto)
        return ResponseEntity.ok(createdProduct)
    }

    @PutMapping("{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody dto: ProductDto): ResponseEntity<ProductDto> {
        val updatedProduct = productService.updateProduct(id, dto)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Unit> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    @GetMapping
    fun getAllProducts(pageable: Pageable): ResponseEntity<Page<ProductDto>> {
        val products = productService.getAllProducts(pageable)
        return ResponseEntity.ok(products)
    }
}

@RestController
@RequestMapping("api/v1/transaction")
class TransactionController(private val transactionService: TransactionService) {

    @PostMapping
    fun createTransaction(@RequestBody dto: TransactionDto): ResponseEntity<TransactionDto> {
        val createdTransaction = transactionService.createTransaction(dto)
        return ResponseEntity.ok(createdTransaction)
    }

    @PutMapping("{id}")
    fun updateTransaction(@PathVariable id: Long, @RequestBody dto: TransactionDto): ResponseEntity<TransactionDto> {
        val updatedTransaction = transactionService.updateTransaction(id, dto)
        return ResponseEntity.ok(updatedTransaction)
    }

    @DeleteMapping("{id}")
    fun deleteTransaction(@PathVariable id: Long): ResponseEntity<Unit> {
        transactionService.deleteTransaction(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("{id}")
    fun getTransactionById(@PathVariable id: Long): ResponseEntity<TransactionDto> {
        val transaction = transactionService.getTransactionById(id)
        return ResponseEntity.ok(transaction)
    }

    @GetMapping
    fun getAllTransactions(pageable: Pageable): ResponseEntity<Page<TransactionDto>> {
        val transactions = transactionService.getAllTransactions(pageable)
        return ResponseEntity.ok(transactions)
    }
}

@RestController
@RequestMapping("api/v1/transaction-item")
class TransactionItemController(private val transactionItemService: TransactionItemService) {

    @PostMapping
    fun createTransactionItem(@RequestBody dto: TransactionItemDto): ResponseEntity<TransactionItemDto> {
        val createdTransactionItem = transactionItemService.createTransactionItem(dto)
        return ResponseEntity.ok(createdTransactionItem)
    }

    @PutMapping("{id}")
    fun updateTransactionItem(
        @PathVariable id: Long,
        @RequestBody dto: TransactionItemDto
    ): ResponseEntity<TransactionItemDto> {
        val updatedTransactionItem = transactionItemService.updateTransactionItem(id, dto)
        return ResponseEntity.ok(updatedTransactionItem)
    }

    @DeleteMapping("{id}")
    fun deleteTransactionItem(@PathVariable id: Long): ResponseEntity<Unit> {
        transactionItemService.deleteTransactionItem(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("{id}")
    fun getTransactionItemById(@PathVariable id: Long): ResponseEntity<TransactionItemDto> {
        val transactionItem = transactionItemService.getTransactionItemById(id)
        return ResponseEntity.ok(transactionItem)
    }

    @GetMapping
    fun getAllTransactionItems(pageable: Pageable): ResponseEntity<Page<TransactionItemDto>> {
        val transactionItems = transactionItemService.getAllTransactionItems(pageable)
        return ResponseEntity.ok(transactionItems)
    }
}

@RestController
@RequestMapping("api/v1/user-payment-transaction")
class UserPaymentTransactionController(private val userPaymentTransactionService: UserPaymentTransactionService) {

    @PostMapping
    fun createUserPaymentTransaction(@RequestBody dto: UserPaymentTransactionDto): ResponseEntity<UserPaymentTransactionDto> {
        val createdUserPaymentTransaction = userPaymentTransactionService.createUserPaymentTransaction(dto)
        return ResponseEntity.ok(createdUserPaymentTransaction)
    }

    @PutMapping("{id}")
    fun updateUserPaymentTransaction(
        @PathVariable id: Long,
        @RequestBody dto: UserPaymentTransactionDto
    ): ResponseEntity<UserPaymentTransactionDto> {
        val updatedUserPaymentTransaction = userPaymentTransactionService.updateUserPaymentTransaction(id, dto)
        return ResponseEntity.ok(updatedUserPaymentTransaction)
    }

    @DeleteMapping("{id}")
    fun deleteUserPaymentTransaction(@PathVariable id: Long): ResponseEntity<Unit> {
        userPaymentTransactionService.deleteUserPaymentTransaction(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("{id}")
    fun getUserPaymentTransactionById(@PathVariable id: Long): ResponseEntity<UserPaymentTransactionDto> {
        val userPaymentTransaction = userPaymentTransactionService.getUserPaymentTransactionById(id)
        return ResponseEntity.ok(userPaymentTransaction)
    }

    @GetMapping
    fun getAllUserPaymentTransactions(pageable: Pageable): ResponseEntity<Page<UserPaymentTransactionDto>> {
        val userPaymentTransactions = userPaymentTransactionService.getAllUserPaymentTransactions(pageable)
        return ResponseEntity.ok(userPaymentTransactions)
    }
}
