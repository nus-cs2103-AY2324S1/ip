package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.util.Storage;

public class DeadlineTest {

    @Test
    public void testDeadlineStringConversion() {
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
                    "[D][ ] Return book (by: Oct 12 2023)",
                    new Deadline(description, byDate).toString()
            );
        } catch (Exception e) {
            assertEquals(
                    String.format("Text '%s' could not be parsed at index 4", byDate),
                    e.getMessage()
            );
        }
    }

    @Test
    public void testDeadlineReschedule() throws DukeException {
        assertEquals(
                "Rescheduled Task: [D][ ] Project Final Grading (by: Oct 10 2023)",
                new Deadline("Project Final Grading", "2023-10-01")
                        .reschedule(
                                "/by 2023-10-10",
                                new Storage("./data/tasks.txt")
                        )
        );

        assertEquals(
                "Rescheduled Task: [D][ ] Wedding (by: Oct 22 2028)",
                new Deadline("Wedding", "2023-10-22")
                        .reschedule(
                                "/by 2028-10-22",
                                new Storage("./data/tasks.txt")
                        )
        );
    }

    @Test
    public void testDeadlineReschedule_invalidFormat_exceptionThrown() {
        try {
            assertEquals(
                    "[D][ ] Wedding (by: Oct 12 2023)",
                    new Deadline("Wedding", "2023-09-22")
                            .reschedule(
                                    "2028-09-22",
                                    new Storage("./data/tasks.txt")
                            )
            );
        } catch (Exception e) {
            assertEquals(
                    "Valid Input Syntax: reschedule {taskNumber} /by {newTime}",
                    e.getMessage()
            );
        }
    }
}
