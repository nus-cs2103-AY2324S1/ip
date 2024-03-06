package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeDatabaseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class StorageTest {

    @Test
    public void loadData_nonExistentFile_throwsNullPointerException() {
        String filePath = "nonexistent_file.txt";
        Storage storage = new Storage(filePath);

        assertThrows(NullPointerException.class, storage::loadData);
    }
    @Test
    public void loadData_existingFile_success() throws DukeDatabaseException {
        String filePath = "test_data.txt";
        Storage storage = new Storage(filePath);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("T | 0 | Task 1\n");
            writer.write("D | 1 | Deadline Task | 2023-08-30 14:30\n");
            writer.write("E | 0 | Event Task | 2023-08-31 15:00\n");
            writer.close();

            ArrayList<Task> loadedTasks = storage.loadData();
            assertEquals(3, loadedTasks.size());
            assertTrue(loadedTasks.get(0) instanceof Todo);
            assertTrue(loadedTasks.get(1) instanceof Deadline);
            assertTrue(loadedTasks.get(2) instanceof Event);
        } catch (IOException e) {
            fail("IOException should not occur.");
        } finally {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void saveData_success() throws DukeDatabaseException, IOException {
        String filePath = "test_data.txt";
        Storage storage = new Storage(filePath);

        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("Test Todo"));
        taskList.addTask(new Deadline("Test Deadline", LocalDateTime.now()));
        taskList.addTask(new Event("Test Event", LocalDateTime.now()));

        storage.saveData(taskList);

        ArrayList<Task> loadedTasks = storage.loadData();
        assertEquals(3, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertTrue(loadedTasks.get(1) instanceof Deadline);
        assertTrue(loadedTasks.get(2) instanceof Event);

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void saveData_ioException_failure() {
        String invalidFilePath = "/invalid/path/test_data.txt";
        Storage storage = new Storage(invalidFilePath);

        // Trying to save data to a directory instead of a file
        assertThrows(IOException.class, () -> {
            storage.saveData(new TaskList(new ArrayList<>()));
        });
    }
}

