package duke.parser;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test class for Parser
 */
public class ParserTest {
    /**
     * Tests parsing of input string to a Task.
     */
    @Test
    public void testStringToTask() {
        try {
            Task deadline = Parser.parseStringToTask("deadline Assignment 0 /by 2023-09-04 23:59");
            assertEquals(new Deadline("Assignment 0", LocalDate.parse("2023-09-04"), LocalTime.parse("23:59")).toString(),
                    deadline.toString());
        } catch (InvalidDescriptionException e) {
        } catch (InvalidDateTimeException e) {
        }
    }

    @Test
    public void testParseInvalidTaskIndex() {
        try {
            Parser.taskToDelete("delete 2", new TaskList());
        } catch (MissingTaskIndexException e) {
        } catch (InvalidTaskIndexException e) {
            assertEquals("Invalid Task Index.", e.getMessage());
        }
    }

}
