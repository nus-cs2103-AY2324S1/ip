package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void parseCommand_emptyTaskList_expectedBehaviour() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertEquals("Wow! You have no tasks in your task list!",
                Parser.parseCommand("list", tasks, ui));
    }

    @Test
    public void parseCommand_addToDo_expectedBehaviour() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertEquals("Got it. I've added this task:\n"
                        + "[T][ ] Task Description\n"
                        + "Now you have 1 task(s) in the list.",
                Parser.parseCommand("todo Task Description", tasks, ui));
    }

    @Test
    public void parseCommand_addEvent_expectedBehaviour() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertEquals("Got it. I've added this task:\n"
                        + "[E][ ] Event Description (from: Aug 23 2023 1800 to: Aug 24 2023 1900)\n"
                        + "Now you have 1 task(s) in the list.",
                Parser.parseCommand(
                        "event Event Description /from 2023-08-23 1800 /to 2023-08-24 1900", tasks, ui));
    }

    @Test
    public void parseCommand_addDeadline_expectedBehaviour() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertEquals("Got it. I've added this task:\n"
                        + "[D][ ] Deadline Description (by: Aug 23 2023 1900)\n"
                        + "Now you have 1 task(s) in the list.",
                Parser.parseCommand("deadline Deadline Description /by 2023-08-23 1900", tasks, ui));
    }

    @Test
    public void parseCommand_markTask_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> {
            Parser.parseCommand("mark 148840", tasks, ui);
        });
    }

    @Test
    public void parseCommand_unmarkTask_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> {
            Parser.parseCommand("unmark 10", tasks, ui);
        });
    }

    @Test
    public void parseCommand_deleteTask_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> {
            Parser.parseCommand("delete 1", tasks, ui);
        });
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertThrows(DukeException.class, () -> {
            Parser.parseCommand("eidcnihevb", tasks, ui);
        });
    }

    @Test
    public void isCreateTaskCommand_invalidCommand_expectedBehaviour() {
        assertFalse(Parser.isCreateTaskCommand("random command"));
    }

    @Test
    public void parseStringToTask_validTodo_expectedBehaviour() throws DukeException {
        Task task = Parser.parseStringToTask("todo A random task description");
        assertEquals("A random task description", task.getDescription());
    }

    @Test
    public void parseStringToTask_validEvent_expectedBehaviour() throws DukeException {
        Task task = Parser.parseStringToTask(
                "event Random Event Description /from 2023-08-23 0000 /to 2023-08-24 0000");
        assertEquals("Random Event Description", task.getDescription());
    }

    @Test
    public void convertToTask_validString_expectedBehaviour() throws DukeException {
        String taskLine = "T || 0 || read book ||  || ";
        Task task = Parser.convertToTask(taskLine);
        assertEquals("read book", task.getDescription());
    }

    @Test
    public void convertToTask_invalidString_exceptionThrown() {
        String invalidTaskLine = "T || 0 | A Task Description || ??????? ||";
        assertThrows(DukeException.class, () -> {
            Task invalidTask = Parser.convertToTask(invalidTaskLine);
        });
    }
}
