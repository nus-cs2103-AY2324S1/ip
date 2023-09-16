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
import tasks.TaskList;
import tasks.TodoTask;
import woof.Woof;

public class ListCommandTest {
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
            ) -> ListCommand.validate("list")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> ListCommand.validate("/list")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> ListCommand.validate("list some argument")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> ListCommand.validate("todo")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> ListCommand.validate("event some task")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> ListCommand.validate("deadline some task"))
        );
    }


    @Test
    public void testExecuteListsTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        LocalDate startDate = LocalDate.parse("2023-01-01", Woof.getDateTimeFormatter());
        LocalDate endDate = LocalDate.parse("2023-12-31", Woof.getDateTimeFormatter());
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", endDate));
        taskList.addTask(new EventTask("Task 3", startDate, endDate));

        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
                + "  1. [T][ ] Task 1" + System.lineSeparator()
                + "  2. [D][ ] Task 2" + System.lineSeparator()
                + "            ~By: 2023-12-31" + System.lineSeparator()
                + "  3. [E][ ] Task 3" + System.lineSeparator()
                + "            ~From: 2023-01-01" + System.lineSeparator()
                + "            ~To  : 2023-12-31" + System.lineSeparator()
                + "You have 3 tasks in the task list." + System.lineSeparator();

        // Act
        String actualOutput = listCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteListsNoTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
                + "You have 0 tasks in the task list." + System.lineSeparator();

        // Act
        String actualOutput = listCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
