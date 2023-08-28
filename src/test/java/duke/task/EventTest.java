package duke.task;

import duke.exception.DukeEndDateBeforeStartDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testCreateNewEvent() {
        try {
            Event task = new Event("career fair", LocalDate.parse("2023-08-10"), LocalDate.parse("2023-08-11"));
            assertEquals(task.toString(), "[E][ ] career fair (from: Aug 10 2023 to: Aug 11 2023)");
        } catch (DukeEndDateBeforeStartDateException e) {
            assertEquals(e.toString(), "OOPS!!! Your start date cannot be after your end date :-(\n");
        }
    }

    @Test
    public void testCreateInvalidNewEvent() {
        try {
            Event task = new Event("career fair", LocalDate.parse("2023-08-10"), LocalDate.parse("2023-08-09"));
            assertEquals(task.toString(), "[E][ ] career fair (from: Aug 10 2023 to: Aug 11 2023)");
        } catch (DukeEndDateBeforeStartDateException e) {
            assertEquals(e.toString(), "OOPS!!! Your start date cannot be after your end date :-(\n");
        }
    }
}
