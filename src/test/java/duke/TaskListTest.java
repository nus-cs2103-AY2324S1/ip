package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    List<Task> list = new ArrayList<>();
    Task task1 = new Todo("abc");
    Task task2 = new Event(LocalDate.of(2023, 8, 16),
            LocalDate.of(2023, 8, 27),
            "read book");
    Task task3 = new Deadline(LocalDate.of(2023, 5, 23), "read book");
    private TaskList taskList = new TaskList(list);

    @BeforeEach
    public void init() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
    }

    @Test
    public void addDeadline_wrongInputFormat_exceptionThrown() {
        try {
            taskList.addDeadline("deadline read book 2023-04-16");
            Task task4 = new Deadline(LocalDate.of(2023, 4, 16), "read book");
            assertEquals(task4.toString(), list.get(3).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Input for deadline doesn't match the expected format." +
                    "\ndeadline ... /by ...", e.getMessage());
        }
    }

    @Test
    public void addDeadline_wrongDateFormat_exceptionThrown() {
        try {
            taskList.addDeadline("deadline read book 2023-16-04");
            Task task4 = new Deadline(LocalDate.of(2023, 4, 16), "read book");
            assertEquals(task4.toString(), list.get(3).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Input for deadline doesn't match the expected format." +
                    "\ndeadline ... /by ...", e.getMessage());
        }
    }

    @Test
    public void addDeadline_correctInput_taskAdded() {
        try {
            taskList.addDeadline("deadline read book /by 2023-04-16");
            Task task4 = new Deadline(LocalDate.of(2023, 4, 16), "read book");
            assertEquals(task4.toString(), list.get(3).toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void markTask_invalidNumber_exceptionThrown() {
        try {
            Task task4 = new Deadline(LocalDate.of(2023, 5, 23), "read book");
            task4.markAsDone();
            taskList.markTask("4");
            assertEquals(task4.toString(), list.get(2).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Invalid number", e.getMessage());
        }
    }

    @Test
    public void markTask_noNumber_exceptionThrown() {
        try {
            Task task4 = new Deadline(LocalDate.of(2023, 5, 23), "read book");
            task4.markAsDone();
            taskList.markTask("read book");
            assertEquals(task4.toString(), list.get(2).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Please key in a number", e.getMessage());
        }
    }

    @Test
    public void markTask_validNumber_taskMarked() {
        try {
            Task task4 = new Deadline(LocalDate.of(2023, 5, 23), "read book");
            task4.markAsDone();
            taskList.markTask("3");
            assertEquals(task4.toString(), list.get(2).toString());
        } catch (Exception e) {
            fail();
        }
    }
}
