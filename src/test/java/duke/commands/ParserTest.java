package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.Parser;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

public class ParserTest {

    @Test
    public void parseTodoTest() throws DukeException {
        String userInput = "todo Sample";
        Command command = Parser.parse(userInput);

        assertEquals(command.getCommandType(), "todo");
        assertEquals("Sample", command.getDescription().orElseGet(null));
    }

    @Test
    public void parseDeadlineTest() throws DukeException {
        String userInput = "deadline Sample /by 2023-12-31";
        Command command = Parser.parse(userInput);

        assertEquals(command.getCommandType(), "deadline");
        assertEquals("Sample", command.getDescription().orElseGet(null));
        assertEquals(LocalDate.parse("2023-12-31"), command.getDeadlineDate().orElseGet(null));
    }

    @Test
    public void parseEventTest() throws DukeException {
        String userInput = "event Sample /from 2023-12-31 /to 2024-01-01";
        Command command = Parser.parse(userInput);

        assertEquals(command.getCommandType(), "event");
        assertEquals("Sample", command.getDescription().orElseGet(null));
        assertEquals(LocalDate.parse("2023-12-31"), command.getEventFromDate().orElseGet(null));
        assertEquals(LocalDate.parse("2024-01-01"), command.getEventToDate().orElseGet(null));
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
