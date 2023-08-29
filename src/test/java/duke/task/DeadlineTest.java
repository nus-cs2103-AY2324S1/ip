package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineStringConversion() throws DukeException {
        assertEquals(
                "[D][ ] Lab submission (by: Aug 31 2023)",
                new Deadline("Lab submission", "2023-08-31").toString()
        );

        assertEquals(
                "[D][ ] Final Project (by: Nov 25 2023)",
                new Deadline("Final Project", "2023-11-25").toString()
        );

        assertEquals(
                "[D][ ] Wedding Planning (by: Mar 5 2028)",
                new Deadline("Wedding Planning", "2028-03-05").toString()
        );
    }
    @Test
    public void testConstructor_invalidDateFormat_exceptionThrown() {
        String description = "Return book";
        String byDate = "2023 10 12";

        try {
            assertEquals(
                    "[E][ ] Return book (by: Oct 12 2023)",
                    new Deadline(description, byDate).toString()
            );
        } catch (Exception e) {
            assertEquals(
                    String.format("Text '%s' could not be parsed at index 4", byDate),
                    e.getMessage()
            );
        }
    }
}
