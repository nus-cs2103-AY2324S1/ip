package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import functional.DukeException;
import functional.Parser;
import functional.Storage;
import functional.TaskList;
import functional.Ui;
import tasks.Task;
import tasks.ToDo;

//Solution below adapted from https://github.com/woojiahao/ip
class FindCommandTest {
    private static final Storage STORAGE = new Storage("tests/tasks.txt");
    private static final Ui UI = new Ui();
    private static final TaskList TASKS = new TaskList<Task>();
    private static final Parser PARSER = new Parser();

    @BeforeAll
    public static void init() {
        TASKS.add(new ToDo("first todo"));
        TASKS.add(new ToDo("weird task"));
        TASKS.add(new ToDo("another task"));
    }

    @Test
    public void execute_noArgument_fail() throws DukeException {
        String input = "find   ";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        assertThrows(DukeException.class, () -> command.execute(TASKS, UI, false, false));
    }

    @Test
    public void execute_noResults_pass() throws DukeException {
        String input = "find abcdefg";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        String output = command.execute(TASKS, UI, false);
        String expected = "____________________________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "____________________________________________________________";
        assertEquals(expected, output);
    }

    @Test
    public void execute_matchingResults_pass() throws DukeException {
        String input = "find task";
        Command command = PARSER.parse(input);
        UI.readCommand(input);
        String output = command.execute(TASKS, UI, false);
        String expected = "____________________________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "2. " + TASKS.get(1).toString() + "\n"
                + "3. " + TASKS.get(2).toString() + "\n"
                + "____________________________________________________________";
        assertEquals(expected, output);
    }
}
