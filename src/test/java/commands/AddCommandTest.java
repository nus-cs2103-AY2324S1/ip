package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import functional.DukeException;
import functional.Parser;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

//Solution below adapted from https://github.com/woojiahao/ip
class AddCommandTest {
    private static final Ui ui = new Ui();
    private static final TaskList tasks = new TaskList<Task>();

    @Test
    public void execute_noArgument_fails() throws DukeException {
        String input = "deadline   ";
        ui.readCommand(input);
        Command command = new Parser().parse(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void testExecuteWithMissingByDate() throws DukeException {
        String input = "deadline do something";
        ui.readCommand(input);
        Command command = new Parser().parse(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_invalidDate_fails() throws DukeException {
        String input = "deadline do something /by tomorrow";
        ui.readCommand(input);
        Command command = new Parser().parse(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_validCommand_pass() throws DukeException {
        TaskList tasklist = new TaskList<Task>();
        String input = "deadline do something /by 19/09/2023 1500";
        Command command = new Parser().parse(input);
        ui.readCommand(input);
        String output = command.execute(tasklist, ui, false, false);
        String expected = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "[D][ ] do something (by: Sep 19 2023 1500)\n"
                + "Now you have 1 tasks in the list.\n"
                + "____________________________________________________________";
        assertEquals(expected, output);
        assertEquals(1, tasklist.size());
    }

    @Test
    public void execute_noArg_fail() throws DukeException {
        String input = "event   ";
        ui.readCommand(input);
        Command command = new Parser().parse(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_missingFromDate_fail() throws DukeException {
        String input = "event do something /to 19/09/2023";
        Command command = new Parser().parse(input);
        ui.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_missingToDate_fail() throws DukeException {
        String input = "event do something /from 19/09/2023";
        Command command = new Parser().parse(input);
        ui.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_invalidFromDateFormat_fail() throws DukeException {
        String input = "event do something /from tomorrow";
        Command command = new Parser().parse(input);
        ui.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_invalidToDateFormat_fail() throws DukeException {
        String input = "event do something /to tomorrow";
        Command command = new Parser().parse(input);
        ui.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, false));
    }

    @Test
    public void execute_validInput_pass() throws DukeException {
        TaskList tasklist = new TaskList<Task>();
        String input = "event do something /from 19/09/2023 1800 /to 2000";
        Command command = new Parser().parse(input);
        ui.readCommand(input);
        String output = command.execute(tasklist, ui, false, false);
        String expected = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "[E][ ] do something (from: Sep 19 2023 1800 to: 2000)\n"
                + "Now you have 1 tasks in the list.\n"
                + "____________________________________________________________";
        assertEquals(expected, output);
        assertEquals(1, tasklist.size());
    }

}
