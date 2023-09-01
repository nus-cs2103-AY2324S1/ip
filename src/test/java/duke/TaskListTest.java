package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Buy groceries");
        tasks.addTask(task);
        assertEquals(1, tasks.getTasks().size());
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList();
        Todo task1 = new Todo("Buy groceries");
        Todo task2 = new Todo("Do dishes");
        tasks.addTask(task1);
        tasks.addTask(task2);
        assertEquals(2, tasks.getTasks().size());
        tasks.deleteTask(1);
        assertEquals(1, tasks.getTasks().size());
    }


}
