package simon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import simon.SimonException;
import simon.TaskList;
import simon.task.Task;

public class ParserTest {
    @Test
    public void parseAddTask_validToDoInput_todoAdded() throws SimonException {
        String input = "todo Sample Task";
        Task task = Parser.parseAddTask(input, Parser.Command.TODO, new TaskList());
        assertEquals("Sample Task", task.getTaskName());
    }

    @Test
    public void parseAddTask_invalidInput_exceptionThrown() {
        String input = "todo";
        assertThrows(SimonException.class, () -> Parser.parseAddTask(input, Parser.Command.TODO, new TaskList()));
    }

    // Assuming the functionality for DEADLINE and EVENT
    @Test
    public void parseAddTask_validDeadlineInput_deadlineAdded() throws SimonException {
        String input = "deadline Sample Task /by 01/01/2023 1800";
        Task task = Parser.parseAddTask(input, Parser.Command.DEADLINE, new TaskList());
        assertEquals("Sample Task", task.getTaskName());
    }

    @Test
    public void parseAddTask_validEventInput_eventAdded() throws SimonException {
        String input = "event Sample Event /from 01/01/2023 1800 /to 02/01/2023 1800";
        Task task = Parser.parseAddTask(input, Parser.Command.EVENT, new TaskList());
        assertEquals("Sample Event", task.getTaskName());
    }

    @Test
    public void parseAddTask_invalidDeadlineFormat_exceptionThrown() {
        String input = "deadline Sample Task";
        assertThrows(SimonException.class, () -> Parser.parseAddTask(input, Parser.Command.DEADLINE, new TaskList()));
    }

    @Test
    public void parseAddTask_invalidEventFormat_exceptionThrown() {
        String input = "event Sample Event /from 01/01/2023 1800";
        assertThrows(SimonException.class, () -> Parser.parseAddTask(input, Parser.Command.EVENT, new TaskList()));
    }
}
