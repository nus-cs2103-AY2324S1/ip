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
        Task[] tasks = {
            new TodoTask(
                "Task 6",
                true
            ),
            new TodoTask(
                "a"
            ),
            new DeadlineTask(
                "b",
                Woof.parseDateTimeIn("2023-01-01")
            ),
            new DeadlineTask(
                "Task 8",
                Woof.parseDateTimeIn("2023-02-03")
            ),
            new TodoTask(
                "c",
                true
            ),
            new TodoTask(
                "d"
            ),
            new TodoTask(
                "Task 1"
            ),
            new EventTask(
                "e",
                Woof.parseDateTimeIn("2023-01-01"),
                Woof.parseDateTimeIn("2023-01-01"),
                true
            ),
            new TodoTask(
                "f"
            ),
            new EventTask(
                "4",
                Woof.parseDateTimeIn("2023-05-05"),
                Woof.parseDateTimeIn("2023-05-06")
            ),
            new DeadlineTask(
                "g",
                Woof.parseDateTimeIn("2023-01-01")
            ),
            new TodoTask(
                "Task 2",
                true
            ),
            new TodoTask(
                "Task 9"
            ),
            new TodoTask(
                "Task 10"
            ),
            new EventTask(
                "h",
                Woof.parseDateTimeIn("2023-02-15"),
                Woof.parseDateTimeIn("2023-02-15"),
                true
            ),
            new TodoTask(
                "i"
            ),
            new TodoTask(
                "Task 3",
                true
            ),
            new EventTask(
                "j",
                Woof.parseDateTimeIn("2023-03-10"),
                Woof.parseDateTimeIn("2023-03-10")
            ),
            new DeadlineTask(
                "k",
                Woof.parseDateTimeIn("2023-04-20")
            ),
            new EventTask(
                "l",
                Woof.parseDateTimeIn("2023-05-05"),
                Woof.parseDateTimeIn("2023-05-06")
            ),
            new TodoTask(
                "Task 5"
            ),
            new TodoTask(
                "Task 7"
            ),
            new DeadlineTask(
                "Task 13",
                Woof.parseDateTimeIn("2023-06-01")
            ),
            new EventTask(
                "Task 12",
                Woof.parseDateTimeIn("2023-07-15"),
                Woof.parseDateTimeIn("2023-07-16"),
                true
            ),
            new TodoTask(
                "Task 11",
                true
            ),
            new TodoTask(
                "Task 19"
            ),
            new DeadlineTask(
                "Task 15",
                Woof.parseDateTimeIn("2023-08-30")
            ),
            new EventTask(
                "Task 16",
                Woof.parseDateTimeIn("2023-09-10"),
                Woof.parseDateTimeIn("2023-09-11")
            ),
            new TodoTask(
                "Task 17"
            ),
            new TodoTask(
                "Task 18",
                true
            ),
            new EventTask(
                "Task 19",
                Woof.parseDateTimeIn("2023-10-20"),
                Woof.parseDateTimeIn("2023-10-21")
            ),
            new DeadlineTask(
                "Task 20",
                Woof.parseDateTimeIn("2023-11-05")
            ),
            new TodoTask(
                "Task m",
                true
            ),
            new DeadlineTask(
                "Task n",
                Woof.parseDateTimeIn("2023-11-15"),
                true
            ),
            new EventTask(
                "Task o",
                Woof.parseDateTimeIn("2023-12-10"),
                Woof.parseDateTimeIn("2023-12-11"),
                true
            ),
            new TodoTask(
                "Task p"
            ),
            new EventTask(
                "Task q",
                Woof.parseDateTimeIn("2024-01-05"),
                Woof.parseDateTimeIn("2024-01-06")
            ),
            new DeadlineTask(
                "Task r",
                Woof.parseDateTimeIn("2024-02-20")
            ),
            new EventTask(
                "Task s",
                Woof.parseDateTimeIn("2024-03-15"),
                Woof.parseDateTimeIn("2024-03-16"),
                true
            ),
            new TodoTask(
                "Task t"
            ),
            new TodoTask(
                "Task u",
                true
            ),
            new EventTask(
                "Task v",
                Woof.parseDateTimeIn("2024-04-20"),
                Woof.parseDateTimeIn("2024-04-21")
            ),
            new DeadlineTask(
                "Task w",
                Woof.parseDateTimeIn("2024-05-10")
            ),
            new EventTask(
                "Task x",
                Woof.parseDateTimeIn("2024-06-05"),
                Woof.parseDateTimeIn("2024-06-06")
            ),
            new TodoTask(
                "Task y"
            ),
            new TodoTask(
                "Task z",
                true
            )
        };
        TaskList taskList = new TaskList(tasks);

        SortCommand sortCommand = new SortCommand("sort");
        String expectedOutput = "Your tasks have been sorted:" + System.lineSeparator()
            + "  1. [D][ ] b" + System.lineSeparator()
            + "            ~By: 01 Jan 2023" + System.lineSeparator()
            + "  2. [D][ ] g" + System.lineSeparator()
            + "            ~By: 01 Jan 2023" + System.lineSeparator()
            + "  3. [D][ ] Task 8" + System.lineSeparator()
            + "            ~By: 03 Feb 2023" + System.lineSeparator()
            + "  4. [D][ ] k" + System.lineSeparator()
            + "            ~By: 20 Apr 2023" + System.lineSeparator()
            + "  5. [D][ ] Task 13" + System.lineSeparator()
            + "            ~By: 01 Jun 2023" + System.lineSeparator()
            + "  6. [D][ ] Task 15" + System.lineSeparator()
            + "            ~By: 30 Aug 2023" + System.lineSeparator()
            + "  7. [D][ ] Task 20" + System.lineSeparator()
            + "            ~By: 05 Nov 2023" + System.lineSeparator()
            + "  8. [D][ ] Task r" + System.lineSeparator()
            + "            ~By: 20 Feb 2024" + System.lineSeparator()
            + "  9. [D][ ] Task w" + System.lineSeparator()
            + "            ~By: 10 May 2024" + System.lineSeparator()
            + " 10. [E][ ] j" + System.lineSeparator()
            + "            ~From: 10 Mar 2023" + System.lineSeparator()
            + "            ~To  : 10 Mar 2023" + System.lineSeparator()
            + " 11. [E][ ] 4" + System.lineSeparator()
            + "            ~From: 05 May 2023" + System.lineSeparator()
            + "            ~To  : 06 May 2023" + System.lineSeparator()
            + " 12. [E][ ] l" + System.lineSeparator()
            + "            ~From: 05 May 2023" + System.lineSeparator()
            + "            ~To  : 06 May 2023" + System.lineSeparator()
            + " 13. [E][ ] Task 16" + System.lineSeparator()
            + "            ~From: 10 Sep 2023" + System.lineSeparator()
            + "            ~To  : 11 Sep 2023" + System.lineSeparator()
            + " 14. [E][ ] Task 19" + System.lineSeparator()
            + "            ~From: 20 Oct 2023" + System.lineSeparator()
            + "            ~To  : 21 Oct 2023" + System.lineSeparator()
            + " 15. [E][ ] Task q" + System.lineSeparator()
            + "            ~From: 05 Jan 2024" + System.lineSeparator()
            + "            ~To  : 06 Jan 2024" + System.lineSeparator()
            + " 16. [E][ ] Task v" + System.lineSeparator()
            + "            ~From: 20 Apr 2024" + System.lineSeparator()
            + "            ~To  : 21 Apr 2024" + System.lineSeparator()
            + " 17. [E][ ] Task x" + System.lineSeparator()
            + "            ~From: 05 Jun 2024" + System.lineSeparator()
            + "            ~To  : 06 Jun 2024" + System.lineSeparator()
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
            + "            ~By: 15 Nov 2023" + System.lineSeparator()
            + " 33. [E][X] e" + System.lineSeparator()
            + "            ~From: 01 Jan 2023" + System.lineSeparator()
            + "            ~To  : 01 Jan 2023" + System.lineSeparator()
            + " 34. [E][X] h" + System.lineSeparator()
            + "            ~From: 15 Feb 2023" + System.lineSeparator()
            + "            ~To  : 15 Feb 2023" + System.lineSeparator()
            + " 35. [E][X] Task 12" + System.lineSeparator()
            + "            ~From: 15 Jul 2023" + System.lineSeparator()
            + "            ~To  : 16 Jul 2023" + System.lineSeparator()
            + " 36. [E][X] Task o" + System.lineSeparator()
            + "            ~From: 10 Dec 2023" + System.lineSeparator()
            + "            ~To  : 11 Dec 2023" + System.lineSeparator()
            + " 37. [E][X] Task s" + System.lineSeparator()
            + "            ~From: 15 Mar 2024" + System.lineSeparator()
            + "            ~To  : 16 Mar 2024" + System.lineSeparator()
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
