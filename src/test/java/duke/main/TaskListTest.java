package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeDuplicatedTaskException;
import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {
    @Test
    public void add_newTask_taskListExpands() {
        TaskList taskList = new TaskList();
        try {
            taskList.add(new Task("todo a"));
        } catch (DukeDuplicatedTaskException e) {
            throw new RuntimeException(e);
        }
        try {
            taskList.add(new Task("todo b"));
        } catch (DukeDuplicatedTaskException e) {
            throw new RuntimeException(e);
        }
        assertEquals(taskList.size(), 2);
    }

    @Test
    public void remove_existingTask_taskListShrinks() {
        TaskList taskList = new TaskList();
        Task a = new Task("todo a");
        Task b = new Task("todo b");
        try {
            taskList.add(a);
        } catch (DukeDuplicatedTaskException e) {
            throw new RuntimeException(e);
        }
        try {
            taskList.add(b);
        } catch (DukeDuplicatedTaskException e) {
            throw new RuntimeException(e);
        }
        Task taskRemoved = taskList.remove(1);
        assertEquals(taskList.size(), 1);
        assertEquals(taskRemoved, b);
    }
}
