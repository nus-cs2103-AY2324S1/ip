import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import duke.DukeException;
import duke.Storage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void testSaveAndLoadTasks() {
        try {
            // Create a sample list of tasks
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(new ToDoTask("Task 1", false));
            tasks.add(new DeadlineTask("Task 2", "2001-02-28", false));
            tasks.add(new EventTask("Task 3", "2001-02-28", "2001-03-28", true));

            // Create a temporary file path for testing
            String testFilePath = "./data/test-duke.txt";

            // Save tasks to the test file
            Storage storage = new Storage(testFilePath);
            storage.saveTasks(tasks);

            // Load tasks from the test file
            ArrayList<Task> loadedTasks = storage.loadTask();

            // Assert that the loaded tasks match the original tasks
            assertEquals(tasks.size(), loadedTasks.size());
            for (int i = 0; i < tasks.size(); i++) {
                assertEquals(tasks.get(i).getDescription(), loadedTasks.get(i).getDescription());
                assertEquals(tasks.get(i).getStatus(), loadedTasks.get(i).getStatus());
            }
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }
}
