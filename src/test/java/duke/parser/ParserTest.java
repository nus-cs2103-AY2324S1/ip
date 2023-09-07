package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a test class for Parser
 */
public class ParserTest {
    /**
     * Tests parsing of input string to a Task.
     */
    @Test
    public void testStringToTask() throws InvalidDescriptionException, InvalidDateTimeException {
        Task deadline = Parser.parseStringToTask("deadline Assignment 0 /by 2023-09-04 23:59");
        assertEquals(new Deadline("Assignment 0", LocalDate.parse("2023-09-04"),
                        LocalTime.parse("23:59")).toString(), deadline.toString());
    }

    @Test
    public void testParseInvalidTaskIndex() {
        try {
            Parser.taskToDelete("delete 2", new TaskList());
            fail();
        } catch (Exception e) {
            assertEquals("Invalid Task Index.", e.getMessage());
        }
    }

}
