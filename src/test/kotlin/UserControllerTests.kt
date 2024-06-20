import com.tschumacher.system.com.tschumacher.system.controllers.UserController
import com.tschumacher.system.com.tschumacher.system.entities.User
import com.tschumacher.system.com.tschumacher.system.services.UserService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.http.HttpStatus

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
})