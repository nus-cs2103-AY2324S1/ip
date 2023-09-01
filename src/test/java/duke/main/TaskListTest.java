package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {
    @Test
    public void add_newTask_taskListExpands() {
        TaskList taskList = new TaskList();
        taskList.add(new Task("todo a"));
        taskList.add(new Task("todo b"));
        assertEquals(taskList.size(), 2);
    }

    @Test
    public void remove_existingTask_taskListShrinks() {
        TaskList taskList = new TaskList();
        Task a = new Task("todo a");
        Task b = new Task("todo b");
        taskList.add(a);
        taskList.add(b);
        Task taskRemoved = taskList.remove(1);
        assertEquals(taskList.size(), 1);
        assertEquals(taskRemoved, b);
    }
}
