import com.tschumacher.system.com.tschumacher.system.controllers.KeyGenerationController
import com.tschumacher.system.com.tschumacher.system.services.KeyGenerationService
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class KeyGenerationControllerTest : StringSpec({

    val keyGenerationService = mockk<KeyGenerationService>()
    val keyGenerationController = KeyGenerationController(keyGenerationService)
    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(keyGenerationController).build()

    "should generate key of specified length" {
        // Arrange
        val length = 16
        val generatedKey = "aRandomGeneratedKey"
        every { keyGenerationService.generateKey(length) } returns generatedKey

        // Act & Assert
        mockMvc.get("/generateKey?length=$length").andExpect {
            status { isOk() }
            content { string(generatedKey) }
        }

        verify(exactly = 1) { keyGenerationService.generateKey(length) }
    }
})