package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import functional.DukeException;
import functional.Parser;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

class DeleteCommandTest {
    private static final TaskList<Task> TASKLIST = new TaskList();
    private static final Parser PARSER = new Parser();
    private static final Ui UI = new Ui();

    @Test
    public void execute_missingTaskIndex_fail() throws DukeException {
        String input = "delete  ";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_nonIntegerIndex_fail() throws DukeException {
        String input = "delete bad";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_negativeIndex_fail() throws DukeException {
        String input = "delete -10";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_indexOutOfBounds_fail() throws DukeException {
        String input = "delete 100";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKLIST, UI, false));
    }

    @Test
    public void execute_withValidIndex_pass() throws DukeException {
        String input = "todo test";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        command.execute(TASKLIST, UI, false, false);
        Task todo = TASKLIST.get(0);
        input = "delete 1";
        command = PARSER.parse(input);
        UI.readCommand(input);
        String output = command.execute(TASKLIST, UI, false);
        String expected = "____________________________________________________________\n"
                + "Noted, I've removed this task:\n"
                + todo.toString() + "\n"
                + "Now you have 0 tasks in the list.\n"
                + "____________________________________________________________";

        assertEquals(expected, output);
        assertEquals(0, TASKLIST.size());
    }
}

