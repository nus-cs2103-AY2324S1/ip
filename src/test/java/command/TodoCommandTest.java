package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.WoofInvalidCommandException;
import tasks.TaskList;
import tasks.TodoTask;


public class TodoCommandTest {
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
        // Act and Assert
        assertAll((
            ) -> assertDoesNotThrow((
            ) -> TodoCommand.validate("todo some task")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> TodoCommand.validate("todo")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> TodoCommand.validate("")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> TodoCommand.validate("event some task")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> TodoCommand.validate("deadline some task"))
        );
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
