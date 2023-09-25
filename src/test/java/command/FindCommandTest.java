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


public class FindCommandTest {
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
            ) -> FindCommand.validate("find keyword")), (
            ) -> assertDoesNotThrow((
            ) -> FindCommand.validate("find keyword extra argument")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> FindCommand.validate("")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> FindCommand.validate("/find keyword")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> FindCommand.validate("find")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> FindCommand.validate("list keyword"))
        );
    }


    @Test
    public void testExecuteFindsMatchingTasks() {
        // Arrange
        Task[] tasks = {
            new TodoTask("Task 1"),
            new TodoTask("Task 2"),
            new TodoTask("Task 3")
        };
        TaskList taskList = new TaskList(tasks);

        FindCommand findCommand = new FindCommand("find 2");
        String expectedOutput = "Here are your matching tasks in your list:" + System.lineSeparator()
                + "  2. [T][ ] Task 2" + System.lineSeparator()
                + "You have 3 tasks in the task list.";

        // Act
        String actualOutput = findCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteFindsMatchingTasksForMultiKeywords() {
        // Arrange
        Task[] tasks = {
            new TodoTask("Eating"),
            new TodoTask("Sleeping"),
            new TodoTask("Drinking"),
            new TodoTask("Task 1"),
            new TodoTask("Task 2"),
            new TodoTask("Task 3"),
            new TodoTask("22")
        };
        TaskList taskList = new TaskList(tasks);

        FindCommand findCommand = new FindCommand("find 2 ing");
        String expectedOutput = "Here are your matching tasks in your list:" + System.lineSeparator()
                + "  1. [T][ ] Eating" + System.lineSeparator()
                + "  2. [T][ ] Sleeping" + System.lineSeparator()
                + "  3. [T][ ] Drinking" + System.lineSeparator()
                + "  5. [T][ ] Task 2" + System.lineSeparator()
                + "  7. [T][ ] 22" + System.lineSeparator()
                + "You have 7 tasks in the task list.";

        // Act
        String actualOutput = findCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteNoMatchingTasks() {
        // Arrange
        Task[] tasks = {
            new TodoTask(
                "Task 1"),
            new DeadlineTask(
                "Task 2",
                LocalDate.parse("2023-12-31")),
            new EventTask(
                "Task 3",
                LocalDate.parse("2023-01-01"),
                LocalDate.parse("2023-12-31"))
        };
        TaskList taskList = new TaskList(tasks);

        FindCommand findCommand = new FindCommand("find NonExistentTask");
        String expectedOutput = "Here are your matching tasks in your list:" + System.lineSeparator()
               + "No tasks matched your keyword(s)!" + System.lineSeparator()
               + "You have 3 tasks in the task list.";

        // Act
        String actualOutput = findCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
