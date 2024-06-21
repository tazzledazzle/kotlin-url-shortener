import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainTests : StringSpec({
    "length of hello should be 5" {
        "hello".length shouldBe 5
    }
})

/*
* 3.1 URL Shortening Process
    Receive URL: The user submits a long URL to be shortened.
    Generate Key: A unique key is generated for the URL.
    Store Mapping: The mapping between the generated key and the original URL is stored in the database.
    Return Shortened URL: The shortened URL (e.g., https://short.ly/abc123) is returned to the user.
3.2 URL Redirection Process
    Receive Shortened URL: The user accesses the shortened URL.
    Retrieve Original URL: The key is extracted from the shortened URL, and the original URL is retrieved from the database.
    Redirect: The user is redirected to the original URL.
3.3 Key Generation
Approaches:
    Random alphanumeric strings.
    Incremental IDs converted to a different base (e.g., base62).
    Collision Handling: Ensure the generated key is unique. If a collision occurs, generate a new key.

*
* */