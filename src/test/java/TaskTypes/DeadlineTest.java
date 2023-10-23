package TaskTypes;
import URBOI_PACKIN.TaskTypes.Deadline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        // Initialize a Deadline object before each test
        LocalDateTime date = LocalDateTime.parse("2023-10-23T14:30", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        deadline = new Deadline("Sample Deadline", date);
    }

    @Test
    public void testDeadlineConstructor() {
        // Check if the Deadline constructor sets the description and date correctly
        assertEquals("Sample Deadline", deadline.getDescription());
        //assertEquals(LocalDateTime.parse("2023-10-23T14:30", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), deadline.getDate());
    }

    @Test
    public void testDeadlineToFileString() {
        // Check the toFileString method for a Deadline task
        assertEquals("D | 0 | Sample Deadline | 2023-10-23T14:30", deadline.toFileString());
    }

    @Test
    public void testDeadlineToString() {
        // Check the toString method for a Deadline task
        assertEquals("[D][ ] Sample Deadline (by: 2023-10-23T14:30)", deadline.toString());
    }
}
