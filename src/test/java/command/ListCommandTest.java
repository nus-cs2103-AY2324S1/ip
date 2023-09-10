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
import tasks.EventTask;
import tasks.TaskList;
import tasks.TodoTask;

public class ListCommandTest {
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
        assertTrue(ListCommand.validate("list"));

        assertFalse(ListCommand.validate("/list"));
        assertFalse(ListCommand.validate("list some argument"));
        assertFalse(ListCommand.validate("todo"));
        assertFalse(ListCommand.validate("event some task"));
        assertFalse(ListCommand.validate("deadline some task"));
    }

    @Test
    public void testExecuteListsTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        LocalDate startDate = LocalDate.parse("2023-01-01", Duke.getDateTimeFormatter());
        LocalDate endDate = LocalDate.parse("2023-12-31", Duke.getDateTimeFormatter());
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", endDate));
        taskList.addTask(new EventTask("Task 3", startDate, endDate));

        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
                + "  1. [T][ ] Task 1" + System.lineSeparator()
                + "  2. [D][ ] Task 2 (by: 2023-12-31)" + System.lineSeparator()
                + "  3. [E][ ] Task 3 (from: 2023-01-01 to 2023-12-31)" + System.lineSeparator()
                + "You have 3 tasks in the task list." + System.lineSeparator();

        // Redirecting System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        listCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        // Assert
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testExecuteListsNoTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
                + "You have 0 tasks in the task list." + System.lineSeparator();

        // Redirecting System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        listCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        // Assert
        assertEquals(expectedOutput, outContent.toString());
    }
}
