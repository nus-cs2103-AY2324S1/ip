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
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
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
            ) -> ListCommand.validate("")), (
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
    public void testExecuteListTasks() {
        // Arrange
        Task[] tasks = {
            new TodoTask(
                "Task 1"),
            new DeadlineTask(
                "Task 2",
                Woof.parseDateTimeIn("2023-12-31")),
            new EventTask(
                "Task 3",
                Woof.parseDateTimeIn("2023-01-01"),
                Woof.parseDateTimeIn("2023-12-31"))
        };
        TaskList taskList = new TaskList(tasks);

        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are all the tasks in your list:" + System.lineSeparator()
                + "  1. [T][ ] Task 1" + System.lineSeparator()
                + "  2. [D][ ] Task 2" + System.lineSeparator()
                + "            ~By: 31 Dec 2023" + System.lineSeparator()
                + "  3. [E][ ] Task 3" + System.lineSeparator()
                + "            ~From: 01 Jan 2023" + System.lineSeparator()
                + "            ~To  : 31 Dec 2023" + System.lineSeparator()
                + "You have 3 tasks in the task list.";

        // Act
        String actualOutput = listCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteListNoTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are all the tasks in your list:" + System.lineSeparator()
                + "You have 0 tasks in the task list.";

        // Act
        String actualOutput = listCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
