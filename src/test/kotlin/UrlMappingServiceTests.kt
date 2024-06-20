import com.tschumacher.system.com.tschumacher.system.entities.UrlMapping
import com.tschumacher.system.com.tschumacher.system.repository.UrlMappingRepository
import com.tschumacher.system.com.tschumacher.system.services.UrlMappingService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


class UrlMappingServiceTest : StringSpec({

    val urlMappingRepository = mockk<UrlMappingRepository>()
    val urlMappingService = UrlMappingService(urlMappingRepository)

    "should return target URL for a given short URL" {
        // Arrange
        val shortUrl = "short1"
        val targetUrl = "https://example.com"
        val urlMapping = UrlMapping(shortUrl = shortUrl, targetUrl = targetUrl)
        every { urlMappingRepository.findByShortUrl(shortUrl) } returns urlMapping

        // Act
        val result = urlMappingService.getTargetUrl(shortUrl)

        // Assert
        result shouldBe targetUrl
        verify(exactly = 1) { urlMappingRepository.findByShortUrl(shortUrl) }
    }

    "should return null if short URL does not exist" {
        // Arrange
        val shortUrl = "short2"
        every { urlMappingRepository.findByShortUrl(shortUrl) } returns null

        // Act
        val result = urlMappingService.getTargetUrl(shortUrl)

        // Assert
        result shouldBe null
        verify(exactly = 1) { urlMappingRepository.findByShortUrl(shortUrl) }
    }

    "should create a new URL mapping" {
        // Arrange
        val shortUrl = "short1"
        val targetUrl = "https://example.com"
        val urlMapping = UrlMapping(shortUrl = shortUrl, targetUrl = targetUrl)
        every { urlMappingRepository.save(urlMapping) } returns urlMapping

        // Act
        val result = urlMappingService.createShortUrl(shortUrl, targetUrl)

        // Assert
        result shouldBe urlMapping
        verify(exactly = 1) { urlMappingRepository.save(urlMapping) }
    }
})