import com.tschumacher.system.com.tschumacher.system.controllers.UrlMappingController
import com.tschumacher.system.com.tschumacher.system.entities.UrlMapping
import com.tschumacher.system.com.tschumacher.system.services.UrlMappingService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.view.RedirectView


class UrlMappingControllerTest : StringSpec({

    val urlMappingService = mockk<UrlMappingService>()
    val urlMappingController = UrlMappingController(urlMappingService)

    "should redirect to target URL for a given short URL" {
        // Arrange
        val shortUrl = "short1"
        val targetUrl = "https://example.com"
        every { urlMappingService.getTargetUrl(shortUrl) } returns targetUrl

        // Act
        val result: RedirectView = urlMappingController.redirect(shortUrl)

        // Assert
        result.url shouldBe targetUrl
        verify(exactly = 1) { urlMappingService.getTargetUrl(shortUrl) }
    }

    "should redirect to /error if short URL does not exist" {
        // Arrange
        val shortUrl = "short2"
        every { urlMappingService.getTargetUrl(shortUrl) } returns null

        // Act
        val result: RedirectView = urlMappingController.redirect(shortUrl)

        // Assert
        result.url shouldBe "/error"
        verify(exactly = 1) { urlMappingService.getTargetUrl(shortUrl) }
    }

    "should create a new URL mapping and return it" {
        // Arrange
        val shortUrl = "short1"
        val targetUrl = "https://example.com"
        val urlMapping = UrlMapping(shortUrl = shortUrl, targetUrl = targetUrl)
        every { urlMappingService.createShortUrl(shortUrl, targetUrl) } returns urlMapping

        // Act
        val response: ResponseEntity<Any> = urlMappingController.createShortUrl(shortUrl, targetUrl)

        // Assert
        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe urlMapping
        verify(exactly = 1) { urlMappingService.createShortUrl(shortUrl, targetUrl) }
    }
})