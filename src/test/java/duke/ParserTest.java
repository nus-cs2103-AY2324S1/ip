package duke;

import duke.command.DukeException;
import duke.task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDeadlineTaskTest() throws DukeException {
        String input = "Finish report /by 2021-10-15";
        Task deadlineTask = Parser.parseDeadlineTask(input, TaskPriority.LOW);
        assertEquals("[D][ ][L] Finish report (by: Oct 15 2021)", deadlineTask.toString());
    }

    @Test
    public void parseEventTaskTest() throws DukeException {
        String input = "Finish report /at 2021-10-15";
        Task eventTask = Parser.parseEventTask(input, TaskPriority.LOW);
        assertEquals("[E][ ][L] Finish report (at: Oct 15 2021)", eventTask.toString());
    }

    @Test
    public void parseTodoTaskTest() throws DukeException {
        String input = "Finish report /from 2019-10-15 /to 2019-10-15";
        Task todoTask = Parser.parseTodoTask(input, TaskPriority.LOW);
        assertEquals("[T][ ][L] Finish report (from: Oct 15 2019 to: Oct 15 2019)", todoTask.toString());
    }

    @Test
    public void addTodoTaskIncorrectDateFormat() {
        String input = "todo Task with wrong date format /from 2021-30-02 /to 2022-10-15";
        try {
            Parser.parseTodoTask(input, TaskPriority.LOW);
            fail("Expected DukeException to be thrown");
        } catch (DukeException e) {
            assertEquals("Invalid date format. Please use YYYY-MM-DD format for dates.", e.getMessage());
        }
    }

    @Test
    public void addEventTaskIncorrectDateFormat() {
        String input = "event Task with wrong date format /at 2021-30-02";
        try {
            Parser.parseEventTask(input, TaskPriority.LOW);
            fail("Expected DukeException to be thrown");
        } catch (DukeException e) {
            assertEquals("Invalid date format. Please use YYYY-MM-DD format for dates.", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTaskIncorrectDateFormat() {
        String input = "deadline Task with wrong date format /by 2021-30-02";
        try {
            Parser.parseDeadlineTask(input, TaskPriority.LOW);
            fail("Expected DukeException to be thrown");
        } catch (DukeException e) {
            assertEquals("Invalid date format. Please use YYYY-MM-DD format for dates.", e.getMessage());
        }
    }
}
