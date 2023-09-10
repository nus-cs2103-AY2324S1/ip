package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.TaskList;
import tasks.TodoTask;


public class TodoCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Restore the original System.out
        System.setOut(originalOut);
    }
    @Test
    public void testValidate() {
        // Arrange, Act, Assert
        assertTrue(TodoCommand.validate("todo some task"));

        assertFalse(TodoCommand.validate("todo"));
        assertFalse(TodoCommand.validate("event some task"));
        assertFalse(TodoCommand.validate("deadline some task"));
    }

    @Test
    public void testExecuteCreateTask() {
        // Arrange
        TaskList taskList = new TaskList(null);
        TodoCommand todoCommand = new TodoCommand("todo TaskName");

        // Act
        todoCommand.execute(taskList);
        TodoTask expectedTask = new TodoTask("TaskName");

        // Assert
        assertEquals(1, taskList.size());
        assertEquals(expectedTask, taskList.getTask(0));
    }

    @Test
    public void testExecuteNoTaskCreatedIfValidationFail() {
        // Arrange
        TaskList taskList = new TaskList(null);
        TodoCommand invalidCommand = new TodoCommand("todo");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        assertEquals(0, taskList.size());
    }
}
