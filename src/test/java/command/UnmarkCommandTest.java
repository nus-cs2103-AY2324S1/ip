package command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;


public class UnmarkCommandTest {
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
        assertTrue(UnmarkCommand.validate("unmark 1", taskList));
        assertTrue(UnmarkCommand.validate("unmark 2", taskList));

        assertFalse(UnmarkCommand.validate("unmark", taskList));
        assertFalse(UnmarkCommand.validate("unmark 0", taskList));
        assertFalse(UnmarkCommand.validate("unmark 3", taskList));
        assertFalse(UnmarkCommand.validate("unmark a", taskList));
    }

    @Test
    public void testExecuteMarksTaskAsDone() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));

        MarkCommand markCommand = new MarkCommand("mark 1");
        UnmarkCommand unmarkCommand = new UnmarkCommand("unmark 1");

        // Act
        markCommand.execute(taskList);
        unmarkCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertFalse(task.isDone());
    }

    @Test
    public void testExecuteNoTaskMarkedIfValidationFails() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));

        MarkCommand markCommand = new MarkCommand("mark 1");
        MarkCommand invalidCommand = new MarkCommand("unmark    1");

        // Act
        markCommand.execute(taskList);
        invalidCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertTrue(task.isDone());
    }
}
