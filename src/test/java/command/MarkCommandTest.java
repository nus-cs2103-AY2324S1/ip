package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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


public class MarkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(new PrintStream(this.outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Restore the original System.out
        System.setOut(this.originalOut);
    }

    @Test
    public void testValidate() {
        // Arrange
        Task[] tasks = {
            new TodoTask("Task 1"),
            new TodoTask("Task 2"),
            new TodoTask("Task 3")
        };
        TaskList taskList = new TaskList(tasks);

        // Act, Assert
        assertAll((
            ) -> assertDoesNotThrow((
            ) -> MarkCommand.validate("mark 1", taskList)), (
            ) -> assertDoesNotThrow((
            ) -> MarkCommand.validate("mark 2", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> MarkCommand.validate("", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> MarkCommand.validate("mark", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> MarkCommand.validate("mark 0", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> MarkCommand.validate("mark 4", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> MarkCommand.validate("mark a", taskList))
        );

    }

    @Test
    public void testExecuteMarksTaskAsDone() {
        // Arrange
        Task[] tasks = {
            new TodoTask("Task 1"),
            new TodoTask("Task 2"),
            new TodoTask("Task 3")
        };
        TaskList taskList = new TaskList(tasks);
        MarkCommand markCommand = new MarkCommand("mark 1");

        // Act
        markCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertTrue(task.isDone());
    }

    @Test
    public void testExecuteNoTaskMarkedIfValidationFails() {
        // Arrange
        Task[] tasks = {
            new TodoTask("Task 1"),
            new TodoTask("Task 2"),
            new TodoTask("Task 3")
        };
        TaskList taskList = new TaskList(tasks);

        MarkCommand invalidCommand = new MarkCommand("mark 0");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertNotEquals("", task.isDone());
    }
}
