package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

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
    public void testStringToTask() {
        try {
            Task deadline = Parser.parseInputToTask("deadline Assignment 0 /by 2023-09-04 23:59", "deadline");
            assertEquals(new Deadline("Assignment 0", LocalDate.parse("2023-09-04"),
                    LocalTime.parse("23:59")).toString(), deadline.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
