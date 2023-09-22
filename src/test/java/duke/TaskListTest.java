package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Class that tests the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests whether task is marked as done.
     */
    @Test
    public void testMarkAsDone() {
        TaskList tasks = new TaskList();
        ToDo sampleToDo = new ToDo("do econs");
        tasks.addTask(sampleToDo);
        tasks.markTaskAsDone(0);
        assertEquals("X", tasks.getTask(0).getStatusIcon());
    }
    /**
     * Tests whether task is marked as not done.
     */
    @Test
    public void testMarkAsNotDone() {
        TaskList tasks = new TaskList();
        ToDo sampleToDo = new ToDo("do econs");
        tasks.addTask(sampleToDo);
        assertEquals(" ", tasks.getTask(0).getStatusIcon());
    }

    /**
     * Tests whether task is added.
     */
    @Test
    public void testAddTodo() {
        TaskList tasks = new TaskList();
        ToDo sampleToDo = new ToDo("do econs");
        tasks.addTask(sampleToDo);
        assertEquals("[T][ ] do econs", tasks.getTask(0).toString());
    }
    /**
     * Tests whether task is added.
     */
    @Test
    public void testAddEvent() {
        TaskList tasks = new TaskList();
        Event sampleEvent = new Event("read book", "2020-03-19 1800", "2020-03-20 1800");
        tasks.addTask(sampleEvent);
        assertEquals("[E][ ] read book (from: Mar 19 2020 06:00 PM to: Mar 20 2020 06:00 PM)", tasks.getTask(0).toString());
    }
    /**
     * Tests whether task is added.
     */
    @Test
    public void testAddDeadline() {
        TaskList tasks = new TaskList();
        Deadline sampleDeadline = new Deadline("homework", "2020-03-19 1800");
        tasks.addTask(sampleDeadline);
        assertEquals("[D][ ] homework (by: Mar 19 2020 06:00 PM)", tasks.getTask(0).toString());
    }
    /**
     * Tests whether task is deleted.
     */
    @Test
    public void testDeleteTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Deadline("homework", "2020-03-19 1800"));
        tasks.addTask(new ToDo("do econs"));
        tasks.addTask(new Event("read book", "2020-03-19 1800", "2020-03-20 1800"));
        tasks.deleteTask(1);
        assertEquals(2, tasks.getSize());
    }

}



