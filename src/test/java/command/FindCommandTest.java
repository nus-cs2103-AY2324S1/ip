package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TaskList;
import tasks.TodoTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FindCommandTest {
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
        assertTrue(FindCommand.validate("find keyword"));
        assertTrue(FindCommand.validate("find keyword extra argument"));

        assertFalse(FindCommand.validate("/find keyword"));
        assertFalse(FindCommand.validate("find"));
        assertFalse(FindCommand.validate("list keyword"));
    }

    @Test
    public void testExecute_FindsMatchingTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        LocalDate startDate = LocalDate.parse("2023-01-01");
        LocalDate endDate = LocalDate.parse("2023-12-31");
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", endDate));
        taskList.addTask(new EventTask("Task 3", startDate, endDate));

        FindCommand findCommand = new FindCommand("find Task 2");
        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator() +
                "  2. [D][ ] Task 2 (by: 2023-12-31)" + System.lineSeparator() +
                "You have 3 tasks in the task list." + System.lineSeparator();

        // Redirecting System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        findCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        // Assert
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testExecute_NoMatchingTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", LocalDate.parse("2023-12-31")));
        taskList.addTask(new EventTask("Task 3", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-12-31")));

        FindCommand findCommand = new FindCommand("find NonExistentTask");
        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator() +
                "No tasks matched your keyword!" + System.lineSeparator() +
                "You have 3 tasks in the task list." + System.lineSeparator();

        // Redirecting System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        findCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        // Assert
        assertEquals(expectedOutput, outContent.toString());
    }
}
