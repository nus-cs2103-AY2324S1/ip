package data.task.builder;


import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.data.task.builder.DeadlineBuilder;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.data.task.Task;

public class DeadlineBuilderTest {
    @Test
    public void buildFromStringTest() throws DukeException {
        DeadlineBuilder deadlineBuilder = new DeadlineBuilder();
        Task t = deadlineBuilder.buildFromString("deadline return book /by 2020-02-02");
        assertEquals("[D][ ] return book (by: Feb 02 2020)", t.toString());
        try {
            deadlineBuilder.buildFromString("deadline return book /by 2020-02-02 12:00");
        } catch (DukeException e) {
            assertEquals("Invalid Input: Invalid date format, should be YYYY-MM-DD", e.getMessage());
        }
    } 
}

