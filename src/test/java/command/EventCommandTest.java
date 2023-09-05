package command;

import duke.Duke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventCommandTest {
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
        assertTrue(EventCommand.validate("event some task /from 2023-01-01 /to 2023-12-31"));

        assertFalse(EventCommand.validate("event"));
        assertFalse(EventCommand.validate("event some task /from 2023-01-01"));
        assertFalse(EventCommand.validate("event some task /to 2023-12-31"));
        assertFalse(EventCommand.validate("deadline some task /from 2023-01-01 /to 2023-12-31"));
    }

    @Test
    public void testExecute_CreateTask() {
        // Arrange
        TaskList taskList = new TaskList(null);
        EventCommand eventCommand = new EventCommand("event TaskName /from 2023-01-01 /to 2023-12-31");

        // Act
        eventCommand.execute(taskList);
        LocalDate startDate = LocalDate.parse("2023-01-01", Duke.DATETIME_FORMATTER);
        LocalDate endDate = LocalDate.parse("2023-12-31", Duke.DATETIME_FORMATTER);
        EventTask expectedTask = new EventTask("TaskName", startDate, endDate);

        // Assert
        Task retrievedTask = taskList.getTask(0);
        assertEquals(retrievedTask, expectedTask);
    }

    @Test
    public void testExecute_NoTaskCreatedIfValidationFail() {
        // Arrange
        TaskList taskList = new TaskList(null);
        EventCommand invalidCommand = new EventCommand("event");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        assertEquals(0, taskList.size());
    }
}
