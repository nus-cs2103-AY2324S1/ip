package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    void testGetStartTime() {
        Event event = new Event("test event getting start time", LocalDate.parse("2023-08-30"),
                LocalDate.parse("2023-08-31"));
        assertEquals(event.getStartTime(), LocalDate.parse("2023-08-30"));
    }

    @Test
    void testGetEndTime() {
        Event event = new Event("test event getting start time", LocalDate.parse("2023-08-30"),
                LocalDate.parse("2023-08-31"));
        assertEquals(event.getEndTime(), LocalDate.parse("2023-08-31"));
    }

    @Test
    void testToString() {
        Event event = new Event("test event", LocalDate.parse("2023-08-30"),
                LocalDate.parse("2023-08-31"));
        assertEquals(event.toString(), "[E][ ] test event (from: Aug 30 2023 to: Aug 31 2023)");
    }

//    @Test
//    void handleEventTask_excessInputs_exceptionThrown() {
//
//    }

//    @Test
//    void handleEventTask_invalidDateInput_exceptionThrown() {
//
//    }
}
