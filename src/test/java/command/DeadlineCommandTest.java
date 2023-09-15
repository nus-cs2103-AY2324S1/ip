package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tasks.DeadlineTask;
import tasks.Task;
import tasks.TaskList;
import woof.Woof;

public class DeadlineCommandTest {
    @Test
    public void testValidate() {
        // Arrange, Act, Assert
        assertEquals("", DeadlineCommand.validate("deadline study /by 2023-01-01"));
        assertEquals("", DeadlineCommand.validate("deadline       study /by 2023-01-01"));

        assertNotEquals("", DeadlineCommand.validate("deadline"));
        assertNotEquals("", DeadlineCommand.validate("deadline study /by 2023/01/01"));
        assertNotEquals("", DeadlineCommand.validate("deadline /by 2023-01-01"));
    }

    @Test
    public void testExecuteCreateTask() {
        // Arrange
        DeadlineCommand deadlineCommand = new DeadlineCommand("deadline some task /by 2023-12-31");
        LocalDate endDate = LocalDate.parse("2023-12-31", Woof.getDateTimeFormatter());
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
