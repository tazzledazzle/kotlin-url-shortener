import com.tschumacher.system.com.tschumacher.system.entities.Event
import com.tschumacher.system.com.tschumacher.system.repository.EventRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.Test


@ExtendWith(SpringExtension::class)
@SpringBootTest
class EventRepositoryTest {

    @Autowired
    lateinit var eventRepository: EventRepository

    @Test
    fun `test findByType`() {
        val eventType = "click"
        val event = Event(type = eventType, data = "button1", timestamp = System.currentTimeMillis())
        eventRepository.save(event)

        val events = eventRepository.findByType(eventType)
        assertEquals(1, events.size)
        assertEquals(eventType, events[0].type)
    }
}