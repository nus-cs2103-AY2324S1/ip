package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.TaskList;
import tasks.TodoTask;
import tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
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
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));

        // Act, Assert
        assertTrue(MarkCommand.validate("mark 1", taskList));
        assertTrue(MarkCommand.validate("mark 2", taskList));

        assertFalse(MarkCommand.validate("mark", taskList));
        assertFalse(MarkCommand.validate("mark 0", taskList));
        assertFalse(MarkCommand.validate("mark 3", taskList));
        assertFalse(MarkCommand.validate("mark a", taskList));
    }

    @Test
    public void testExecute_MarksTaskAsDone() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));

        MarkCommand markCommand = new MarkCommand("mark 1");

        // Act
        markCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertTrue(task.isDone());
    }

    @Test
    public void testExecute_NoTaskMarkedIfValidationFails() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));

        MarkCommand invalidCommand = new MarkCommand("mark 0");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertFalse(task.isDone());
    }
}
