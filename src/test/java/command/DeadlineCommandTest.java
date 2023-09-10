package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Duke;
import tasks.DeadlineTask;
import tasks.Task;
import tasks.TaskList;

public class DeadlineCommandTest {
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
        assertTrue(DeadlineCommand.validate("deadline study /by 2023-01-01"));
        assertTrue(DeadlineCommand.validate("deadline       study /by 2023-01-01"));

        assertFalse(DeadlineCommand.validate("deadline"));
        assertFalse(DeadlineCommand.validate("deadline study /by 2023/01/01"));
        assertFalse(DeadlineCommand.validate("deadline /by 2023/01/01"));
    }

    @Test
    public void testExecuteCreateTask() {
        // Arrange
        DeadlineCommand deadlineCommand = new DeadlineCommand("deadline some task /by 2023-12-31");
        LocalDate endDate = LocalDate.parse("2023-12-31", Duke.getDateTimeFormatter());
        Task expectedTask = new DeadlineTask("some task", endDate);
        TaskList taskList = new TaskList(null);

        // Act
        deadlineCommand.execute(taskList);

        // Assert
        Task retrievedTask = taskList.getTask(0);
        assertEquals(expectedTask, retrievedTask);
    }

    @Test
    public void testExecuteNoTaskCreatedIfValidationFail() {
        // Arrange
        TaskList taskList = new TaskList(null);
        TodoCommand invalidCommand = new TodoCommand("dealine");

        // Act
        invalidCommand.execute(taskList);

        // Assert
        assertEquals(0, taskList.size());
    }
}
