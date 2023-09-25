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


public class NullCommandTest {
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
        // Arrange, Act, Assert
        assertAll((
            ) -> assertDoesNotThrow((
            ) -> NullCommand.validate("hehe")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> NullCommand.validate("")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> NullCommand.validate("    ")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> NullCommand.validate("unmark task 1")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> NullCommand.validate("todo abc"))
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
        UnmarkCommand unmarkCommand = new UnmarkCommand("unmark 1");

        // Act
        markCommand.execute(taskList);
        unmarkCommand.execute(taskList);

        // Assert
        Task task = taskList.getTask(0);
        assertNotEquals("", task.isDone());
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
