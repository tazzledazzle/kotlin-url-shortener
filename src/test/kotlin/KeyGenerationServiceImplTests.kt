import com.tschumacher.system.com.tschumacher.system.services.KeyGenerationServiceImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldHaveLength
import io.kotest.matchers.string.shouldMatch


class KeyGenerationServiceImplTest : StringSpec({

    val keyGenerationService = KeyGenerationServiceImpl()

    "should generate key of specified length" {
        val length = 16
        val key = keyGenerationService.generateKey(length)

        key shouldHaveLength length
    }

    "should generate unique keys" {
        val length = 16
        val key1 = keyGenerationService.generateKey(length)
        val key2 = keyGenerationService.generateKey(length)

        key1 shouldNotBe key2
    }

    "should generate key with allowed characters" {
        val length = 16
        val key = keyGenerationService.generateKey(length)
        val regex = Regex("[A-Za-z0-9]{$length}")

        key shouldMatch regex
    }
})