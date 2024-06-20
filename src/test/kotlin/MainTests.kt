import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainTests : StringSpec({
    "length of hello should be 5" {
        "hello".length shouldBe 5
    }
})