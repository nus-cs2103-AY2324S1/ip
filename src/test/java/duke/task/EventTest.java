package duke.task;

import duke.exception.DukeEndDateBeforeStartDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addEvent_success() {
        Event task = new Event("career fair", LocalDate.parse("2023-08-10"), LocalDate.parse("2023-08-11"));
        assertEquals(task.toString(), "[E][ ] career fair (from: Aug 10 2023 to: Aug 11 2023)");
    }
}
