import com.tschumacher.system.com.tschumacher.system.controllers.UserController
import com.tschumacher.system.com.tschumacher.system.entities.User
import com.tschumacher.system.com.tschumacher.system.services.UserService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UserControllerTest : StringSpec({

    val userService = mockk<UserService>()
    val userController = UserController(userService)
    "should return all users and return no exception" {
        // Arrange
        every { userService.getAllUsers() } returns emptyList<User>()
        // Act
        val response = userController.getAllUsers()
        // Assert
        response shouldBe emptyList<User>()
        verify(exactly = 1) { userService.getAllUsers() }
    }
    "should delete user and return no content status" {
        // Arrange
        val userId = 1L
        every { userService.deleteUser(userId) } just runs

        // Act
        val response = userController.deleteUser(userId)

        // Assert
        response.statusCode shouldBe HttpStatus.NO_CONTENT
        verify(exactly = 1) { userService.deleteUser(userId) }
    }

    "should return user by id" {
        // Arrange
        val userId = 1L
        val user = User(id = userId, name = "John Doe", email = "john.doe@example.com")
        every { userService.getUserById(userId) } returns user

        // Act
        val response: ResponseEntity<User> = userController.getUserById(userId)

        // Assert
        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe user
        verify(exactly = 1) { userService.getUserById(userId) }
    }

    "should return not found when user by id does not exist" {
        // Arrange
        val userId = 2L
        every { userService.getUserById(userId) } returns null

        // Act
        val response: ResponseEntity<User> = userController.getUserById(userId)

        // Assert
        response.statusCode shouldBe HttpStatus.NOT_FOUND
        response.body shouldBe null
        verify(exactly = 1) { userService.getUserById(userId) }
    }

    "should create user and return created status" {
        // Arrange
        val user = User(name = "Jane Doe", email = "jane.doe@example.com")
        val createdUser = user.copy(id = 1L)
        every { userService.createUser(user) } returns createdUser

        // Act
        val response: ResponseEntity<User> = userController.createUser(user)

        // Assert
        response.statusCode shouldBe HttpStatus.CREATED
        response.body shouldBe createdUser
        verify(exactly = 1) { userService.createUser(user) }
    }

    "should update user and return ok status" {
        // Arrange
        val userId = 1L
        val user = User(id = userId, name = "Jane Doe", email = "jane.doe@example.com")
        every { userService.updateUser(userId, user) } returns user

        // Act
        val response: ResponseEntity<User> = userController.updateUser(userId, user)

        // Assert
        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe user
        verify(exactly = 1) { userService.updateUser(userId, user) }
    }

    "should return not found when updating non-existing user" {
        // Arrange
        val userId = 2L
        val user = User(id = userId, name = "Jane Doe", email = "jane.doe@example.com")
        every { userService.updateUser(userId, user) } returns null

        // Act
        val response: ResponseEntity<User> = userController.updateUser(userId, user)

        // Assert
        response.statusCode shouldBe HttpStatus.NOT_FOUND
        response.body shouldBe null
        verify(exactly = 1) { userService.updateUser(userId, user) }
    }
})