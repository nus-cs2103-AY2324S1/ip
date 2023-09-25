package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.WoofInvalidCommandException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;
import woof.Woof;

public class DeleteCommandTest {
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
        //Arrange
        LocalDate startDate = Woof.parseDateTimeIn("2023-01-01");
        LocalDate endDate = Woof.parseDateTimeIn("2023-12-31");
        Task task1 = new TodoTask("some task 1");
        Task task2 = new DeadlineTask("some task 2", endDate);
        Task task3 = new EventTask("some task 2", startDate, endDate);
        TaskList taskList = new TaskList(new Task[]{task1, task2, task3});

        // Act, Assert
        assertAll((
            ) -> assertDoesNotThrow((
            ) -> DeleteCommand.validate("delete 1", taskList)), (
            ) -> assertDoesNotThrow((
            ) -> DeleteCommand.validate("delete 2", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> DeleteCommand.validate("", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> DeleteCommand.validate("delete", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> DeleteCommand.validate("delete a", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> DeleteCommand.validate("delete 0", taskList)), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> DeleteCommand.validate("delete 4", taskList))
        );
    }

    @Test
    public void testExecuteDeleteTask() {
        // Arrange
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        TaskList taskList = new TaskList(new Task[]{new TodoTask("some task 1")});

        deleteCommand.execute(taskList);

        // Assert
        assertEquals(taskList.size(), 0);
    }

    @Test
    public void testExecuteNoTaskDeletedIfValidationFails() {
        // Arrange
        TaskList taskList = new TaskList(new Task[] {new TodoTask("Task 1")});
        DeleteCommand invalidCommand = new DeleteCommand("delete 1?");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        assertEquals(1, taskList.size());
    }
}
