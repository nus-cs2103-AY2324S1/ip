package duke;

import duke.exceptions.StorageCreationException;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests the {@code TaskList} class.
 */
public class TaskListTest {
    /**
     * Tests whether the task list appropriately lists tasks.
     */
    @Test
    public void listTasks_tasksInList_success() {
        TaskList taskList = new TaskList();
        Storage storage = new StorageStub();
        taskList.setStorage(storage);
        try {
            taskList.loadTasks();
            assertEquals(" Here are the tasks in your list:\n 1.[T][ ] Go for health checkup\n 2" +
                    ".[D][ ] CS2103T quiz (by: 5 Sep 2023, 1200)\n 3.[E][ ] CS2103T meeting " +
                    "(from: 5 Sep 2023, 0000 to: 5 Sep 2023, 1200)", taskList.listTasks());
        } catch (IOException | StorageCreationException e) {
            fail(e);
        }
    }

    /**
     * Tests whether the task list appropriately handles no tasks.
     */
    @Test
    public void listTasks_emptyList_success() {
        List<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        Storage storage = new StorageStub();
        try {
            storage.updateStorage(tasks);
            taskList.setStorage(storage);
            taskList.loadTasks();
            assertEquals(" Here are the tasks in your list:\n", taskList.listTasks());
        } catch (IOException | StorageCreationException e) {
            fail(e);
        }
    }
}