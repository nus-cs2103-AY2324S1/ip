package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidStartEndDatetimeException;

public class TaskTest {

    @Test
    public void testGetDescription() {
        // todo task
        assertEquals("borrow book", new Todo("borrow book").getDescription());

        // deadline task
        assertEquals("return book",
                new Deadline("return book", "2023-06-06 1800").getDescription());

        // event task
        try {
            assertEquals("project meeting",
                    new Event("project meeting", "2023-08-06 1400", "2023-08-06 1600")
                            .getDescription());
        } catch (InvalidStartEndDatetimeException e) {
            final String expectedMsg = "☹ OOPS!!! Start datetime cannot be >= end datetime.";
            assertEquals(expectedMsg, e.getMessage());
        }
    }

    @Test
    public void testGetStatusIcon() {
        // not done
        assertEquals(" ", new Todo("borrow book", false).getStatusIcon());

        // done
        assertEquals("X", new Todo("borrow book", true).getStatusIcon());
    }
}
