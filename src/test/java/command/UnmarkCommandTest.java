package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.WoofInvalidCommandException;
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
        assertAll((
            ) -> assertDoesNotThrow((
            ) -> UnmarkCommand.validate("unmark 1", taskList)), (
            ) -> assertDoesNotThrow((
            ) -> UnmarkCommand.validate("unmark 2", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> UnmarkCommand.validate("unmark", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> UnmarkCommand.validate("unmark 0", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> UnmarkCommand.validate("unmark 3", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> UnmarkCommand.validate("unmark a", taskList))
        );
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
