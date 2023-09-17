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

public class SortCommandTest {
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
            ) -> SortCommand.validate("sort")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> SortCommand.validate("")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> SortCommand.validate("/sort")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> SortCommand.validate("sort some argument")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> SortCommand.validate("todo")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> SortCommand.validate("event some task")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> SortCommand.validate("deadline some task"))
        );
    }


    @Test
    public void testExecuteSortTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        taskList.addTask(new TodoTask(
            "Task 6",
            true));
        taskList.addTask(new TodoTask(
            "a"));
        taskList.addTask(new DeadlineTask(
            "b",
            LocalDate.parse("2023-01-01", Woof.getDateTimeFormatter())));
        taskList.addTask(new DeadlineTask(
            "Task 8",
            LocalDate.parse("2023-02-03", Woof.getDateTimeFormatter())));
        taskList.addTask(new TodoTask(
            "c",
            true));
        taskList.addTask(new TodoTask(
            "d"));
        taskList.addTask(new TodoTask(
            "Task 1"));
        taskList.addTask(new EventTask(
            "e",
            LocalDate.parse("2023-01-01", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-01-01", Woof.getDateTimeFormatter()),
            true));
        taskList.addTask(new TodoTask(
            "f"));
        taskList.addTask(new EventTask(
            "4",
            LocalDate.parse("2023-05-05", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-05-06", Woof.getDateTimeFormatter())));
        taskList.addTask(new DeadlineTask(
            "g",
            LocalDate.parse("2023-01-01", Woof.getDateTimeFormatter())));
        taskList.addTask(new TodoTask(
            "Task 2",
            true));
        taskList.addTask(new TodoTask(
            "Task 9"));
        taskList.addTask(new TodoTask(
            "Task 10"));
        taskList.addTask(new EventTask(
            "h",
            LocalDate.parse("2023-02-15", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-02-15", Woof.getDateTimeFormatter()),
            true));
        taskList.addTask(new TodoTask(
            "i"));
        taskList.addTask(new TodoTask(
            "Task 3",
            true));
        taskList.addTask(new EventTask(
            "j",
            LocalDate.parse("2023-03-10", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-03-10", Woof.getDateTimeFormatter())));
        taskList.addTask(new DeadlineTask(
            "k",
            LocalDate.parse("2023-04-20", Woof.getDateTimeFormatter())));
        taskList.addTask(new EventTask(
            "l",
            LocalDate.parse("2023-05-05", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-05-06", Woof.getDateTimeFormatter())));
        taskList.addTask(new TodoTask(
            "Task 5"));
        taskList.addTask(new TodoTask(
            "Task 7"));
        taskList.addTask(new DeadlineTask(
            "Task 13",
            LocalDate.parse("2023-06-01", Woof.getDateTimeFormatter())));
        taskList.addTask(new EventTask(
            "Task 12",
            LocalDate.parse("2023-07-15", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-07-16", Woof.getDateTimeFormatter()),
            true));
        taskList.addTask(new TodoTask(
            "Task 11",
            true));
        taskList.addTask(new TodoTask(
            "Task 19"));
        taskList.addTask(new DeadlineTask(
            "Task 15",
            LocalDate.parse("2023-08-30", Woof.getDateTimeFormatter())));
        taskList.addTask(new EventTask(
            "Task 16",
            LocalDate.parse("2023-09-10", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-09-11", Woof.getDateTimeFormatter())));
        taskList.addTask(new TodoTask(
            "Task 17"));
        taskList.addTask(new TodoTask(
            "Task 18",
            true));
        taskList.addTask(new EventTask(
            "Task 19",
            LocalDate.parse("2023-10-20", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-10-21", Woof.getDateTimeFormatter())));
        taskList.addTask(new DeadlineTask(
            "Task 20",
            LocalDate.parse("2023-11-05", Woof.getDateTimeFormatter())));
        taskList.addTask(new TodoTask(
            "Task m",
            true));
        taskList.addTask(new DeadlineTask(
            "Task n",
            LocalDate.parse("2023-11-15", Woof.getDateTimeFormatter()),
            true));
        taskList.addTask(new EventTask(
            "Task o",
            LocalDate.parse("2023-12-10", Woof.getDateTimeFormatter()),
            LocalDate.parse("2023-12-11", Woof.getDateTimeFormatter()),
            true));
        taskList.addTask(new TodoTask(
            "Task p"));
        taskList.addTask(new EventTask(
            "Task q",
            LocalDate.parse("2024-01-05", Woof.getDateTimeFormatter()),
            LocalDate.parse("2024-01-06", Woof.getDateTimeFormatter())));
        taskList.addTask(new DeadlineTask(
            "Task r",
            LocalDate.parse("2024-02-20", Woof.getDateTimeFormatter())));
        taskList.addTask(new EventTask(
            "Task s",
            LocalDate.parse("2024-03-15", Woof.getDateTimeFormatter()),
            LocalDate.parse("2024-03-16", Woof.getDateTimeFormatter()),
            true));
        taskList.addTask(new TodoTask(
            "Task t"));
        taskList.addTask(new TodoTask(
            "Task u",
            true));
        taskList.addTask(new EventTask(
            "Task v",
            LocalDate.parse("2024-04-20", Woof.getDateTimeFormatter()),
            LocalDate.parse("2024-04-21", Woof.getDateTimeFormatter())));
        taskList.addTask(new DeadlineTask(
            "Task w",
            LocalDate.parse("2024-05-10", Woof.getDateTimeFormatter())));
        taskList.addTask(new EventTask(
            "Task x",
            LocalDate.parse("2024-06-05", Woof.getDateTimeFormatter()),
            LocalDate.parse("2024-06-06", Woof.getDateTimeFormatter())));
        taskList.addTask(new TodoTask(
            "Task y"));
        taskList.addTask(new TodoTask(
            "Task z",
            true));

        SortCommand sortCommand = new SortCommand("sort");
        String expectedOutput = "Your tasks have been sorted:" + System.lineSeparator()
            + "  1. [D][ ] b" + System.lineSeparator()
            + "            ~By: 2023-01-01" + System.lineSeparator()
            + "  2. [D][ ] g" + System.lineSeparator()
            + "            ~By: 2023-01-01" + System.lineSeparator()
            + "  3. [D][ ] Task 8" + System.lineSeparator()
            + "            ~By: 2023-02-03" + System.lineSeparator()
            + "  4. [D][ ] k" + System.lineSeparator()
            + "            ~By: 2023-04-20" + System.lineSeparator()
            + "  5. [D][ ] Task 13" + System.lineSeparator()
            + "            ~By: 2023-06-01" + System.lineSeparator()
            + "  6. [D][ ] Task 15" + System.lineSeparator()
            + "            ~By: 2023-08-30" + System.lineSeparator()
            + "  7. [D][ ] Task 20" + System.lineSeparator()
            + "            ~By: 2023-11-05" + System.lineSeparator()
            + "  8. [D][ ] Task r" + System.lineSeparator()
            + "            ~By: 2024-02-20" + System.lineSeparator()
            + "  9. [D][ ] Task w" + System.lineSeparator()
            + "            ~By: 2024-05-10" + System.lineSeparator()
            + " 10. [E][ ] j" + System.lineSeparator()
            + "            ~From: 2023-03-10" + System.lineSeparator()
            + "            ~To  : 2023-03-10" + System.lineSeparator()
            + " 11. [E][ ] 4" + System.lineSeparator()
            + "            ~From: 2023-05-05" + System.lineSeparator()
            + "            ~To  : 2023-05-06" + System.lineSeparator()
            + " 12. [E][ ] l" + System.lineSeparator()
            + "            ~From: 2023-05-05" + System.lineSeparator()
            + "            ~To  : 2023-05-06" + System.lineSeparator()
            + " 13. [E][ ] Task 16" + System.lineSeparator()
            + "            ~From: 2023-09-10" + System.lineSeparator()
            + "            ~To  : 2023-09-11" + System.lineSeparator()
            + " 14. [E][ ] Task 19" + System.lineSeparator()
            + "            ~From: 2023-10-20" + System.lineSeparator()
            + "            ~To  : 2023-10-21" + System.lineSeparator()
            + " 15. [E][ ] Task q" + System.lineSeparator()
            + "            ~From: 2024-01-05" + System.lineSeparator()
            + "            ~To  : 2024-01-06" + System.lineSeparator()
            + " 16. [E][ ] Task v" + System.lineSeparator()
            + "            ~From: 2024-04-20" + System.lineSeparator()
            + "            ~To  : 2024-04-21" + System.lineSeparator()
            + " 17. [E][ ] Task x" + System.lineSeparator()
            + "            ~From: 2024-06-05" + System.lineSeparator()
            + "            ~To  : 2024-06-06" + System.lineSeparator()
            + " 18. [T][ ] Task 1" + System.lineSeparator()
            + " 19. [T][ ] Task 10" + System.lineSeparator()
            + " 20. [T][ ] Task 17" + System.lineSeparator()
            + " 21. [T][ ] Task 19" + System.lineSeparator()
            + " 22. [T][ ] Task 5" + System.lineSeparator()
            + " 23. [T][ ] Task 7" + System.lineSeparator()
            + " 24. [T][ ] Task 9" + System.lineSeparator()
            + " 25. [T][ ] Task p" + System.lineSeparator()
            + " 26. [T][ ] Task t" + System.lineSeparator()
            + " 27. [T][ ] Task y" + System.lineSeparator()
            + " 28. [T][ ] a" + System.lineSeparator()
            + " 29. [T][ ] d" + System.lineSeparator()
            + " 30. [T][ ] f" + System.lineSeparator()
            + " 31. [T][ ] i" + System.lineSeparator()
            + " 32. [D][X] Task n" + System.lineSeparator()
            + "            ~By: 2023-11-15" + System.lineSeparator()
            + " 33. [E][X] e" + System.lineSeparator()
            + "            ~From: 2023-01-01" + System.lineSeparator()
            + "            ~To  : 2023-01-01" + System.lineSeparator()
            + " 34. [E][X] h" + System.lineSeparator()
            + "            ~From: 2023-02-15" + System.lineSeparator()
            + "            ~To  : 2023-02-15" + System.lineSeparator()
            + " 35. [E][X] Task 12" + System.lineSeparator()
            + "            ~From: 2023-07-15" + System.lineSeparator()
            + "            ~To  : 2023-07-16" + System.lineSeparator()
            + " 36. [E][X] Task o" + System.lineSeparator()
            + "            ~From: 2023-12-10" + System.lineSeparator()
            + "            ~To  : 2023-12-11" + System.lineSeparator()
            + " 37. [E][X] Task s" + System.lineSeparator()
            + "            ~From: 2024-03-15" + System.lineSeparator()
            + "            ~To  : 2024-03-16" + System.lineSeparator()
            + " 38. [T][X] Task 11" + System.lineSeparator()
            + " 39. [T][X] Task 18" + System.lineSeparator()
            + " 40. [T][X] Task 2" + System.lineSeparator()
            + " 41. [T][X] Task 3" + System.lineSeparator()
            + " 42. [T][X] Task 6" + System.lineSeparator()
            + " 43. [T][X] Task m" + System.lineSeparator()
            + " 44. [T][X] Task u" + System.lineSeparator()
            + " 45. [T][X] Task z" + System.lineSeparator()
            + " 46. [T][X] c" + System.lineSeparator()
            + "You have 46 tasks in the task list.";

        // Act
        String actualOutput = sortCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteSortNoTasks() {
        // Arrange
        TaskList taskList = new TaskList(null);
        SortCommand sortCommand = new SortCommand("sort");
        String expectedOutput = "Your tasks have been sorted:" + System.lineSeparator()
                + "There's nothing to sort... really" + System.lineSeparator()
                + "You have 0 tasks in the task list.";

        // Act
        String actualOutput = sortCommand.execute(taskList);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
