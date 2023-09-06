package chadbod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ChadBodTest {
    @Test
    public void createEvent_validEvent_success() throws Exception {
        // Tests for correct handling of basic input
        Event expectedFirstEvent = new Event("party", LocalDateTime.parse("2023-08-28T00:00:00"),
                LocalDateTime.parse("2023-08-28T02:00:00"));
        Event actualFirstEvent = ChadBod.createEvent("party /from 2023-08-28T00:00:00 /to 2023-08-28T02:00:00");
        assertEquals(expectedFirstEvent.toString(), actualFirstEvent.toString());
        assertEquals(expectedFirstEvent.toFileString(), actualFirstEvent.toFileString());
        // Tests for correct handling of 24-hour format, past dates
        Event expectedSecondEvent = new Event("sky dive", LocalDateTime.parse("2001-08-28T13:59:59"),
                LocalDateTime.parse("2001-08-28T14:00:00"));
        Event actualSecondEvent = ChadBod.createEvent(
                "sky dive /from 2001-08-28T13:59:59 /to 2001-08-28T14:00:00");
        assertEquals(expectedSecondEvent.toString(), actualSecondEvent.toString());
        assertEquals(expectedSecondEvent.toFileString(), expectedSecondEvent.toFileString());
        // Tests for no abnormal behaviour when handling numbers or forward slash
        Event expectedThirdEvent = new Event("Take bus 87/E", LocalDateTime.parse("2023-09-01T06:00:00"),
                LocalDateTime.parse("2023-09-01T06:01:00"));
        Event actualThirdEvent = ChadBod.createEvent(
                "Take bus 87/E /from 2023-09-01T06:00:00 /to 2023-09-01T06:01:00");
        assertEquals(expectedThirdEvent.toString(), actualThirdEvent.toString());
        assertEquals(expectedThirdEvent.toFileString(), actualThirdEvent.toFileString());
    }

    @Test
    public void createEvent_invalidEvent_exceptionThrown() {
        // Tests for exception thrown when missing "/from" in input pattern
        try {
            ChadBod.createEvent("party from 2023-08-28T00:00:00 /to 2023-08-28T02:00:00");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! Event timings cannot be empty.", e.getMessage());
        }
        // Tests for exception thrown when missing "/to in input pattern
        try {
            ChadBod.createEvent("party /from 2023-08-28T00:00:00 to 2023-08-28T02:00:00");
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! Event from and to timings cannot be empty.", e.getMessage());
        }
        // Tests for exception thrown with incorrect date input in input pattern
        try {
            ChadBod.createEvent("party /from 28/08/2023T00:00:00 /to 28/08/2023T02:00:00");
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! Deadline due date/time not in ISO format (e.g. 2007-12-03T10:15:30).",
                    e.getMessage());
        }
    }
}
