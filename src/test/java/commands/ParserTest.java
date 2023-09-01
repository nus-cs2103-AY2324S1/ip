package commands;

import duke.commands.Command;
import duke.commands.Parser;
import duke.exception.DeadlineException;
import duke.exception.EventException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseTodoTest() throws DukeException {
        String userInput = "todo Sample";
        Command command = Parser.parse(userInput);

        assertEquals(command.getCommandType(), "todo");
        assertEquals("Sample", command.getDescription());
    }

    @Test
    public void parseDeadlineTest() throws DukeException {
        String userInput = "deadline Sample /by 2023-12-31";
        Command command = Parser.parse(userInput);

        assertEquals(command.getCommandType(), "deadline");
        assertEquals("Sample", command.getDescription());
        assertEquals(LocalDate.parse("2023-12-31"), command.getDeadlineDate());
    }

    @Test
    public void parseEventTest() throws DukeException {
        String userInput = "event Sample /from 2023-12-31 /to 2024-01-01";
        Command command = Parser.parse(userInput);

        assertEquals(command.getCommandType(), "event");
        assertEquals("Sample", command.getDescription());
        assertEquals(LocalDate.parse("2023-12-31"), command.getEventFromDate());
        assertEquals(LocalDate.parse("2024-01-01"), command.getEventToDate());
    }

    @Test
    public void parseInvalidCommandTest() {
        String userInput = "invalidCommand";
        assertThrows(DukeException.class, () -> Parser.parse(userInput));
    }

    @Test
    public void parseFileStringTodoTest() {
        String taskData = "T | 1 | Sample Todo";
        Task task = Parser.parseFileString(taskData);

        assertTrue(task instanceof Todo);
        assertEquals("Sample Todo", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    public void parseFileStringDeadlineTest() {
        String taskData = "D | 0 | Sample Deadline | 2023-12-31";
        Task task = Parser.parseFileString(taskData);

        assertTrue(task instanceof Deadline);
        assertEquals("Sample Deadline", task.getDescription());
    }
}