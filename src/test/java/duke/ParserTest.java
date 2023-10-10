package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseAndAddTask_AddTodoTask() {
        TaskList taskList = new TaskList();
        String userInput = "todo Buy groceries";

        Parser.parseAndAddTask(userInput, taskList);

        assertEquals(1, taskList.getTotalTasks());
        assertTrue(taskList.getTasks().get(0) instanceof ToDo);
    }

    @Test
    public void testParseAndAddTask_AddDeadlineTask() {
        TaskList taskList = new TaskList();
        String userInput = "deadline Return book /by 2023-12-31";

        Parser.parseAndAddTask(userInput, taskList);

        assertEquals(1, taskList.getTotalTasks());
        assertTrue(taskList.getTasks().get(0) instanceof Deadline);
    }

    @Test
    public void testParseAndAddTask_AddEventTask() {
        TaskList taskList = new TaskList();
        String userInput = "event Birthday party /from 2023-08-30 15:00 /to 2023-08-30 18:00";

        Parser.parseAndAddTask(userInput, taskList);

        assertEquals(1, taskList.getTotalTasks());
        assertTrue(taskList.getTasks().get(0) instanceof Event);
    }

    @Test
    public void testParseAndAddTask_InvalidCommand() {
        TaskList taskList = new TaskList();
        String userInput = "invalidcommand";

        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseAndAddTask(userInput, taskList);
        });
    }

    @Test
    public void testParseAndAddTask_MarkTask() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Test task", false);
        String userInput = "mark 1";

        Parser.parseAndAddTask(userInput, taskList);

        assertTrue(taskList.getTasks().get(0).isDone());
    }

    @Test
    public void testParseAndAddTask_UnmarkTask() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Test task", true);
        String userInput = "unmark 1";

        Parser.parseAndAddTask(userInput, taskList);

        assertFalse(taskList.getTasks().get(0).isDone());
    }

    @Test
    public void testParseAndAddTask_DeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Test task", false);
        String userInput = "delete 1";

        Parser.parseAndAddTask(userInput, taskList);

        assertEquals(0, taskList.getTotalTasks());
    }

}
