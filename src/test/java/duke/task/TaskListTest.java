package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Buy groceries");
        taskList.add(task1);
        assertEquals(task1, taskList.getTask(1));

        Task task2 = new Deadline("CS2103T", LocalDate.now(), LocalTime.now());
        taskList.add(task2);
        assertEquals(task2, taskList.getTask(2));

        Task task3 = new Event("SLF", "Aug 20", "Aug 22");
        taskList.add(task3);
        assertEquals(task3, taskList.getTask(3));
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Read a book");
        Task task2 = new ToDo("Clean the room");

        taskList.add(task1);
        taskList.add(task2);

        taskList.remove(1);

        assertEquals(1, taskList.getNumberOfTasks());
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    public void testGetTask() {
        TaskList tasklist = new TaskList();
        Task task1 = new ToDo("Read a book");
        Task task2 = new ToDo("Clean the room");
        tasklist.add(task1);
        tasklist.add(task2);
        assertEquals(2, tasklist.getNumberOfTasks());
    }
}
