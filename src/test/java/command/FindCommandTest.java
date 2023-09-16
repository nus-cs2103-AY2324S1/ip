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
        TaskList taskList = new TaskList(null);
        LocalDate startDate = LocalDate.parse("2023-01-01");
        LocalDate endDate = LocalDate.parse("2023-12-31");
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", endDate));
        taskList.addTask(new EventTask("Task 3", startDate, endDate));

        FindCommand findCommand = new FindCommand("find 2");
        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator()
                + "  2. [D][ ] Task 2" + System.lineSeparator()
                + "            ~By: 2023-12-31" + System.lineSeparator()
                + "You have 3 tasks in the task list." + System.lineSeparator();

        // Act
        String actualOutput = findCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteFindsMatchingTasksForMultiKeywords() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Eating"));
        taskList.addTask(new TodoTask("Sleeping"));
        taskList.addTask(new TodoTask("Drinking"));
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));
        taskList.addTask(new TodoTask("Task 3"));
        taskList.addTask(new TodoTask("22"));


        FindCommand findCommand = new FindCommand("find 2 ing");
        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator()
                + "  1. [T][ ] Eating" + System.lineSeparator()
                + "  2. [T][ ] Sleeping" + System.lineSeparator()
                + "  3. [T][ ] Drinking" + System.lineSeparator()
                + "  5. [T][ ] Task 2" + System.lineSeparator()
                + "  7. [T][ ] 22" + System.lineSeparator()
                + "You have 7 tasks in the task list." + System.lineSeparator();

        // Act
        String actualOutput = findCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteNoMatchingTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", LocalDate.parse("2023-12-31")));
        taskList.addTask(new EventTask("Task 3", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-12-31")));

        FindCommand findCommand = new FindCommand("find NonExistentTask");
        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator()
               + "No tasks matched your keyword!" + System.lineSeparator()
               + "You have 3 tasks in the task list." + System.lineSeparator();

        // Act
        String actualOutput = findCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
