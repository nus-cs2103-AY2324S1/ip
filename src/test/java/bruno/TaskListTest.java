package bruno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;
import java.util.List;

import org.junit.jupiter.api.Test;

import bruno.exceptions.BrunoEmptyException;
import bruno.exceptions.BrunoException;
import bruno.exceptions.BrunoIndexOutOfBoundsException;
import bruno.exceptions.BrunoIntegerMismatchException;
import bruno.exceptions.BrunoMissingDeadlineException;
import bruno.exceptions.BrunoMissingEventException;
import bruno.exceptions.BrunoNegativeArgException;
import bruno.task.Deadline;
import bruno.task.Event;
import bruno.task.Task;
import bruno.task.ToDo;

public class TaskListTest {
    private final UI ui = new UI();
    private final Storage storage = new Storage("data/", "bruno.txt");
    private final TaskList taskList = new TaskList(this.storage, this.ui);

    @Test
    void testAddToDo() {
        try {
            taskList.addToDo("todo buy groceries");
            List<Task> tasks = taskList.list;
            assertEquals(1, tasks.size());
            assertTrue(tasks.get(0) instanceof ToDo);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testAddToDo_withoutDescription_exceptionThrown() {
        Exception exception = assertThrows(BrunoEmptyException.class, () -> taskList.addToDo("todo"));
        assertEquals("Ruff Ruff! Description of todo cannot be empty! ❌", exception.getMessage());
    }

    @Test
    void testAddDeadline() {
        try {
            taskList.addDeadline("deadline assignment /by 2023-08-30 19:00");
            List<Task> tasks = taskList.list;
            assertEquals(1, tasks.size());
            assertTrue(tasks.get(0) instanceof Deadline);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testAddDeadline_withoutDescription_exceptionThrown() {
        Exception exception = assertThrows(BrunoEmptyException.class, () -> taskList.addDeadline("deadline "
                + "/by 2023-08-30 19:00"));
        assertEquals("Ruff Ruff! Description of deadline cannot be empty! ❌", exception.getMessage());
    }

    @Test
    void testAddDeadline_withoutDeadline_exceptionThrown() {
        Exception exception = assertThrows(BrunoMissingDeadlineException.class, () -> taskList.addDeadline(
                "deadline work"));
        assertEquals("Ruff Ruff! You cannot add a Deadline task without setting the deadline! ❌",
                exception.getMessage());
    }

    @Test
    void testAddDeadline_incorrectDateFormat_exceptionThrown() {
        assertThrows(DateTimeException.class, () -> taskList.addDeadline("deadline "
                + "work /by 29-08-2023 19:00"));
    }

    @Test
    void testAddEvent() {
        try {
            taskList.addEvent("event presentation /from 2023-08-31 10:00 /to 2023-08-31 11:00");
            List<Task> tasks = taskList.list;
            assertEquals(1, tasks.size());
            assertTrue(tasks.get(0) instanceof Event);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testAddEvent_withoutDescription_exceptionThrown() {
        Exception e = assertThrows(BrunoEmptyException.class, () -> taskList.addEvent("event /from "
                + "2023-08-31 10:00 /to "
                + "2023-08-31 11:00"));
        assertEquals("Ruff Ruff! Description of event cannot be empty! ❌", e.getMessage());
    }

    @Test
    void testAddEvent_withoutStartEndTime_exceptionThrown() {
        Exception e = assertThrows(BrunoMissingEventException.class, () -> taskList.addEvent("event work"));
        assertEquals("Ruff Ruff! You cannot add an Event task without setting start and end time! ❌", e.getMessage());
    }

    @Test
    void testAddEvent_incorrectDateFormat_exceptionThrown() {
        assertThrows(DateTimeException.class, () -> taskList.addEvent("event work /from 30-08-2023 18:00 "
                + "/to 30-08-2023 19:00"));
    }

    @Test
    void testMarkTask() {
        try {
            List<Task> tasks = taskList.list;
            taskList.addToDo("todo work");
            assertFalse(tasks.get(0).isDone);
            taskList.markTask("mark 1");
            assertTrue(tasks.get(0).isDone);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testMarkTask_stringArg_exceptionThrown() {
        taskList.list.add(new ToDo("work"));
        assertThrows(BrunoIntegerMismatchException.class, () -> taskList.markTask("mark abc"));
    }

    @Test
    void testMarkTask_negativeArg_exceptionThrown() {
        taskList.list.add(new Deadline("post-lecture quiz", "2023-08-30 18:00"));
        assertThrows(BrunoNegativeArgException.class, () -> taskList.markTask("mark -1"));
    }

    @Test
    void testMarkTask_outOfBoundsArg_exceptionThrown() {
        taskList.list.add(new ToDo("project"));
        assertThrows(BrunoIndexOutOfBoundsException.class, () -> taskList.markTask("mark 2"));
    }

    @Test
    void testUnmarkTask() {
        try {
            List<Task> tasks = taskList.list;
            Task task = new ToDo("todo work");
            task.isDone = true;
            tasks.add(task);
            assertTrue(tasks.get(0).isDone);
            taskList.unmarkTask("unmark 1");
            assertFalse(tasks.get(0).isDone);
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testUnmarkTask_stringArg_exceptionThrown() {
        taskList.list.add(new ToDo("work"));
        taskList.list.get(0).isDone = true;
        assertThrows(BrunoIntegerMismatchException.class, () -> taskList.unmarkTask("unmark abc"));
    }

    @Test
    void testUnmarkTask_negativeArg_exceptionThrown() {
        taskList.list.add(new Deadline("post-lecture quiz", "2023-08-30 18:00"));
        taskList.list.get(0).isDone = true;
        assertThrows(BrunoNegativeArgException.class, () -> taskList.unmarkTask("unmark -1"));
    }

    @Test
    void testUnmarkTask_outOfBoundsArg_exceptionThrown() {
        taskList.list.add(new ToDo("project"));
        taskList.list.get(0).isDone = true;
        assertThrows(BrunoIndexOutOfBoundsException.class, () -> taskList.unmarkTask("unmark 2"));
    }

    @Test
    void testDeleteTask() {
        try {
            taskList.list.add(new Event("hackathon", "2023-08-29 10:00", "2023-09-01 10:00"));
            taskList.list.add(new ToDo("marathon"));
            taskList.deleteTask("delete 2");
            assertEquals(1, taskList.list.size());
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testDeleteTask_stringArg_exceptionThrown() {
        taskList.list.add(new Deadline("project", "2023-09-01 18:00"));
        assertThrows(BrunoIntegerMismatchException.class, () -> taskList.deleteTask("delete a"));
    }

    @Test
    void testDeleteTask_outOfBoundsArg_exceptionThrown() {
        taskList.list.add(new ToDo("debug project"));
        assertThrows(BrunoIndexOutOfBoundsException.class, () -> taskList.deleteTask("delete 2"));
    }

    @Test
    void testDeleteTask_negativeArg_exceptionThrown() {
        taskList.list.add(new Event("career fair", "2023-08-29 17:00", "2023-08-31 17:00"));
        assertThrows(BrunoNegativeArgException.class, () -> taskList.deleteTask("delete -1"));
    }
}
