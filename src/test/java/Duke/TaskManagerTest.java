package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Task;

/**
 * The type Task manager test.
 */
public class TaskManagerTest {

    /**
     * To test whether tasks are added successfully.
     */
    @Test
    public void addTaskTest() {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        taskManager.todo("finish homework");
        int size = taskManager.displayList().size();
        assertEquals("[T][ ] finish homework", taskManager.displayList().get(size - 1).getStatusIcon());
    }

    /**
     * To test that find() method working correctly.
     */
    @Test
    public void findTaskTest() {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        taskManager.todo("finish homework");
        taskManager.todo("submit project");
        taskManager.todo("finish quiz");
        ArrayList<Task> tasksFound = taskManager.find("finish");
        String matchingTask1 = tasksFound.get(0).getStatusIcon();
        String matchingTask2 = tasksFound.get(1).getStatusIcon();
        assertEquals("[T][ ] finish homework", matchingTask1);
        assertEquals("[T][ ] finish quiz", matchingTask2);
    }

    /**
     * To test that update() method working correctly.
     */
    @Test
    public void updateTaskTest() {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        taskManager.todo("finish homework");
        taskManager.update(0, "deadline submit project /by 22-09-2023 10:00");
        assertEquals("[D][ ] submit project (by: 22 Sep 2023 10:00)",
                taskManager.displayList().get(0).getStatusIcon());
    }

    /**
     * To test that delete() method working correctly.
     */
    @Test
    public void deleteTaskTest() {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        taskManager.todo("finish homework");
        taskManager.todo("submit project");
        taskManager.todo("finish quiz");
        taskManager.delete(1);
        assertEquals(2, taskManager.displayList().size());
    }
}
