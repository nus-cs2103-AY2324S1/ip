package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getAll_tasks_success() {
        Task task1 = new Todo("something");
        Task task2 = new Deadline("new deadline", LocalDate.parse("2023-08-31"));
        Task task3 = new Event("new event", "today", "tomorrow");

        TaskList tl = new TaskList(new ArrayList<>(Arrays.asList(task1, task2, task3)));
        ArrayList<Task> expectedList = new ArrayList<>(
                Arrays.asList(task1, task2, task3)
        );
        assertEquals(expectedList, tl.getAll());
    }

    @Test
    public void add_todoTask_success() throws LukeException {
        TaskList tl = new TaskList();
        String todoPrefix = "todo ";

        String todo1Name = "newtask";
        Task todo1 = tl.add(todoPrefix + todo1Name);
        Todo expectedTodo1 = new Todo(false, todo1Name);
        assertEquals(expectedTodo1, todo1);

        String todo2Name = "new task";
        Task todo2 = tl.add(todoPrefix + todo2Name);
        Todo expectedTodo2 = new Todo(false, todo2Name);
        assertEquals(expectedTodo2, todo2);

        ArrayList<Task> expectedTaskList= new ArrayList<>(Arrays.asList(todo1, todo2));
        assertEquals(expectedTaskList, tl.getAll());

    }

    @Test
    public void add_deadlineTask_success() throws LukeException {
        TaskList tl = new TaskList();
        String deadlinePrefix = "deadline ";
        String deadlineByArgConnector = " /by ";

        String deadline1Name = "newdeadline";
        String deadline1DueDate = "2011-09-12";
        Task deadline1 = tl.add(deadlinePrefix
                + deadline1Name + deadlineByArgConnector + deadline1DueDate);
        Deadline expectedDeadline1 = new Deadline(
                false,
                deadline1Name,
                LocalDate.parse(deadline1DueDate));
        assertEquals(expectedDeadline1, deadline1);

        String deadline2Name = "another deadline 2013-12-01";
        String deadline2DueDate = "1960-12-25";
        Task deadline2 = tl.add(deadlinePrefix
                + deadline2Name + deadlineByArgConnector + deadline2DueDate);
        Deadline expectedDeadline2 = new Deadline(
                false,
                deadline2Name,
                LocalDate.parse(deadline2DueDate));
        assertEquals(expectedDeadline2, deadline2);

        ArrayList<Task> expectedTaskList= new ArrayList<>(Arrays.asList(deadline1, deadline2));
        assertEquals(expectedTaskList, tl.getAll());
    }

    @Test
    public void add_eventTask_success() throws LukeException {
        TaskList tl = new TaskList();
        String eventPrefix = "event ";
        String eventFromArgConnector = " /from ";
        String eventToArgConnector = " /to ";


        String event1Name = "event";
        String event1From = "sometime";
        String event1To = "anothertime";
        Task event1 = tl.add(eventPrefix + event1Name
                + eventFromArgConnector + event1From
                + eventToArgConnector + event1To);
        Event expectedEvent1 = new Event(false, event1Name, event1From, event1To);
        assertEquals(expectedEvent1, event1);


        String event2Name = "another event";
        String event2From = "this timing";
        String event2To = "that timing";
        Task event2 = tl.add(eventPrefix + event2Name
                + eventFromArgConnector + event2From
                + eventToArgConnector + event2To);
        Event expectedEvent2 = new Event(false, event2Name, event2From, event2To);
        assertEquals(expectedEvent2, event2);

        ArrayList<Task> expectedTaskList= new ArrayList<>(Arrays.asList(event1, event2));
        assertEquals(expectedTaskList, tl.getAll());
    }

    @Test
    public void add_badCommands_exceptionThrown() {
        TaskList tl = new TaskList();

        String unknownCommand = "todoo something";
        try {
            tl.add(unknownCommand);
            fail();
        } catch (LukeException e) {
            assertEquals("Unknown command", e.getMessage());
        }

        String invalidFormatCommand = "deadline something";
        try {
            tl.add(invalidFormatCommand);
            fail();
        } catch (LukeException e) {
            // Error message will be tested for in DeadlineTest
        }
    }

    @Test
    public void delete_correctNumber_success() throws LukeException{
        String deletePrefix = "delete ";

        Task seedTask1 = new Todo("First");
        Task seedTask2 = new Todo("Second");
        Task seedTask3 = new Todo("Third");

        TaskList tl = new TaskList(new ArrayList<>(Arrays.asList(
                seedTask1, seedTask2, seedTask3
        )));

        Task deletedTask = tl.delete(deletePrefix + "2");
        assertEquals(deletedTask, seedTask2);

        ArrayList<Task> expectedTaskList = new ArrayList<>(Arrays.asList(seedTask1, seedTask3));
        assertEquals(expectedTaskList, tl.getAll());
    }

    @Test
    public void delete_outOfRangeNumber_exceptionThrown() {
        String deletePrefix = "delete ";

        TaskList tl = new TaskList(new ArrayList<>(Arrays.asList(
                new Todo("first"),
                new Todo("second"),
                new Todo("third")
        )));

        String expectedErrorMsg = "Invalid task number: no such task";
        try {
            tl.delete(deletePrefix + "4");
            fail();
        } catch (LukeException e) {
            assertEquals(expectedErrorMsg, e.getMessage());
        }

        try {
            tl.delete(deletePrefix + "0");
            fail();
        } catch (LukeException e) {
            assertEquals(expectedErrorMsg, e.getMessage());
        }
    }

    @Test
    public void delete_badFormat_exceptionThrown() {
        TaskList tl = new TaskList(new ArrayList<>(Arrays.asList(
                new Todo("first"),
                new Todo("second"),
                new Todo("third")
        )));

        try {
            tl.delete("delete first");
            fail();
        } catch (LukeException e) {
            String expectedErrorMsg = "Invalid format. Usage: delete {task_number}";
            assertEquals(expectedErrorMsg, e.getMessage());
        }

        try {
            tl.delete("delete 1 2");
            fail();
        } catch (LukeException e) {
            String expectedErrorMsg = "Invalid format. Usage: delete {task_number}";
            assertEquals(expectedErrorMsg, e.getMessage());
        }

        try {
            tl.delete("delete ");
            fail();
        } catch (LukeException e) {
            String expectedErrorMsg = "Task number cannot be empty.";
            assertEquals(expectedErrorMsg, e.getMessage());
        }
    }
}
