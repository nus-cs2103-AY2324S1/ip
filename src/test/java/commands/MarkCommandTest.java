package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import functional.DukeException;
import functional.Parser;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

//Solution below adapted from https://github.com/woojiahao/ip
class MarkCommandTest {
    private static final TaskList<Task> TASKLIST = new TaskList();
    private static final Parser PARSER = new Parser();
    private static final Ui UI = new Ui();

    @Test
    public void execute_missingIndex_fail() throws DukeException {
        String input = "mark  ";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_NonIntegerIndex_fail() throws DukeException {
        String input = "mark bad";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_negativeIndex_fail() throws DukeException {
        String input = "mark -10";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_indexOutOfBounds_fail() throws DukeException {
        String input = "mark 100";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_validIndex_pass() throws DukeException {
        String input = "todo task";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        command.execute(TASKLIST, UI, false);
        input = "mark 1";
        command = PARSER.parse(input);
        UI.readCommand(input);
        String output = command.execute(TASKLIST, UI, false);
        Task task = TASKLIST.get(0);
        String expected = "____________________________________________________________\n"
                + "OK, I've marked this task as done:\n"
                + task.toString() + "\n"
                + "____________________________________________________________";
        assertEquals(expected, output);
        assertTrue(task.isMarked());
        assertEquals(LocalDate.now(), task.getDateTime());
    }
}
