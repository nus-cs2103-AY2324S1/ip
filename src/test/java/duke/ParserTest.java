package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testIsCreateTaskCommandTodo() {
        assertTrue(Parser.isCreateTaskCommand("todo task"));
    }

    @Test
    public void testIsCreateTaskCommandEvent() {
        assertTrue(Parser.isCreateTaskCommand("event event task /from 2023-08-23 1800 /to 2023-08-24 0000"));
    }

    @Test
    public void testIsCreateTaskCommandDeadline() {
        assertTrue(Parser.isCreateTaskCommand("deadline deadline task /by 2023-08-23 0000"));
    }

    @Test
    public void testIsCreateTaskCommandInvalid() {
        assertFalse(Parser.isCreateTaskCommand("random command"));
    }

    @Test
    public void testParseCreateTaskInputTodo() throws DukeException {
        Task task = Parser.parseCreateTaskInput("todo A random task description");
        assertEquals("A random task description", task.getDescription());
    }

    @Test
    public void testParseCreateTaskInputEvent() throws DukeException {
        Task task = Parser.parseCreateTaskInput("event Random Event Description /from 2023-08-23 0000 /to 2023-08-24 0000");
        assertEquals("Random Event Description", task.getDescription());
    }

    @Test
    public void testParseCommandInvalid() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> {
            Parser.parseCommand("eidcnihevb", tasks, ui);
        });
    }

    @Test
    public void testParseCommandList() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("list", tasks, ui));
    }

    @Test
    public void testParseCommandTodo() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("todo Task Description", tasks, ui));
    }

    @Test
    public void testParseCommandEvent() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("event Event Description /from 2023-08-23 1800 /to 2023-08-24 1900", tasks, ui));
    }

    @Test
    public void testParseCommandDeadline() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("deadline Deadline Description /by 2023-08-23 1900", tasks, ui));
    }

    @Test
    public void testParseCommandMark() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("mark 1", tasks, ui));
    }

    @Test
    public void testParseCommandUnmark() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("unmark 1", tasks, ui));
    }

    @Test
    public void testParseCommandDelete() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertTrue(Parser.parseCommand("delete 1", tasks, ui));
    }

    @Test
    public void testParseCommandUnknown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> {
            Parser.parseCommand("unknown", tasks, ui);
        });
    }

    @Test
    public void testConvertToTaskValid() throws DukeException {
        String taskLine = "T || 0 || read book ||  || ";
        Task task = Parser.convertToTask(taskLine);
        assertEquals("read book", task.getDescription());
    }

    @Test
    public void testConvertToTaskInvalid() {
        String invalidTaskLine = "T || 0 | A Task Description || ??????? ||";
        assertThrows(DukeException.class, () -> {
            Task invalidTask = Parser.convertToTask(invalidTaskLine);
        });
    }
}
