package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void loadTasksFromFileTest() throws DukeException, IOException {

        // Prepare a temporary test file
        File tempFile = File.createTempFile("test", ".txt");
        String tempFilePath = tempFile.getAbsolutePath();
        Storage storage = new Storage(tempFilePath);

        // Create a sample task list
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(new ToDo("Sample ToDo Task"));
        expectedTaskList.add(new Deadline("Sample Deadline Task", "31/08/2023"));
        expectedTaskList.add(new Event("Sample Event Task",
                "31/08/2023 1600","31/08/2023 1800"));

        // Save the sample task list to the temporary test file
        TaskList taskListToSave = new TaskList(expectedTaskList);
        storage.saveTasksToFile(taskListToSave);

        // Load tasks from the temporary test file
        ArrayList<Task> loadedTaskList = storage.loadTasksFromFile();

        // Clean up the temporary file
        tempFile.delete();

        // Verify the loaded task list matches the expected task list
        assertEquals(expectedTaskList.size(), loadedTaskList.size());
        for (int i = 0; i < expectedTaskList.size(); i++) {
            assertEquals(expectedTaskList.get(i).getDescription(), loadedTaskList.get(i).getDescription());
            assertEquals(expectedTaskList.get(i).isDone(), loadedTaskList.get(i).isDone());
        }
    }

    @Test
    public void saveTasksToFileTest() throws DukeException, IOException {
        // Prepare a temporary test file
        File tempFile = File.createTempFile("test", ".txt");
        String tempFilePath = tempFile.getAbsolutePath();
        Storage storage = new Storage(tempFilePath);

        // Create a sample task list
        ArrayList<Task> taskListToSave = new ArrayList<>();
        taskListToSave.add(new ToDo("Sample ToDo Task"));
        taskListToSave.add(new Deadline("Sample Deadline Task", "31/08/2023"));
        taskListToSave.add(new Event("Sample Event Task",
                "31/08/2023 1600","31/08/2023 1800"));

        // Save the sample task list to the temporary test file
        storage.saveTasksToFile(new TaskList(taskListToSave));

        // Load tasks from the temporary test file
        ArrayList<Task> loadedTaskList = storage.loadTasksFromFile();

        // Clean up the temporary file
        tempFile.delete();

        // Verify the loaded task list matches the originally saved task list
        assertEquals(taskListToSave.size(), loadedTaskList.size());
        for (int i = 0; i < taskListToSave.size(); i++) {
            assertEquals(taskListToSave.get(i).getDescription(), loadedTaskList.get(i).getDescription());
            assertEquals(taskListToSave.get(i).isDone(), loadedTaskList.get(i).isDone());
        }
    }
}
