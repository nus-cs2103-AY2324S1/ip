package chadbod;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChadBodTest {
    @Test
    public void createEvent_validEvent_success() throws Exception {
        Event expected = new Event("party", LocalDateTime.parse("2023-08-28T00:00:00"),
                LocalDateTime.parse("2023-08-28T02:00:00"));
        Event actual = ChadBod.createEvent("party /from 2023-08-28T00:00:00 /to 2023-08-28T02:00:00");
        assertEquals(expected.toString(), actual.toString());
        assertEquals(expected.toFileString(), actual.toFileString());
    }

    @Test
    public void parseCommand_invalidTask_exceptionThrown() {
        try {
            ChadBod.createEvent("party from 2023-08-28T00:00:00 /to 2023-08-28T02:00:00");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! Event timings cannot be empty.", e.getMessage());
        }
    }
}