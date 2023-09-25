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
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import woof.Woof;

public class EventCommandTest {
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
            ) -> EventCommand.validate("event some task /from 2023-01-01 /to 2023-12-31")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> EventCommand.validate("event some task /from 2023-01-01 /to 2022-12-31")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> EventCommand.validate("event")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> EventCommand.validate("")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> EventCommand.validate("event some task /from 2023-01-01")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> EventCommand.validate("event some task /to 2023-12-31")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> EventCommand.validate("event /from 2023-01-01 /to 2023-12-31"))
        );
    }

    @Test
    public void testExecuteCreateTask() {
        // Arrange
        TaskList taskList = new TaskList(null);
        EventCommand eventCommand = new EventCommand("event TaskName /from 2023-01-01 /to 2023-12-31");

        // Act
        eventCommand.execute(taskList);
        LocalDate startDate = Woof.parseDateTimeIn("2023-01-01");
        LocalDate endDate = Woof.parseDateTimeIn("2023-12-31");
        EventTask expectedTask = new EventTask("TaskName", startDate, endDate);

        // Assert
        Task retrievedTask = taskList.getTask(0);
        assertEquals(retrievedTask, expectedTask);
    }

    @Test
    public void testExecuteNoTaskCreatedIfValidationFail() {
        // Arrange
        TaskList taskList = new TaskList(null);
        EventCommand invalidCommand = new EventCommand("event");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        assertEquals(0, taskList.size());
    }
}
