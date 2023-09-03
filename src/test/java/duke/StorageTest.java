package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary test file
        testFile = File.createTempFile("testfile", ".txt");
        String testFilePath = testFile.getAbsolutePath();

        // Write some sample task data to the test file
        FileWriter fileWriter = new FileWriter(testFile);
        fileWriter.write("T | 1 | Buy groceries\n");
        fileWriter.write("D | 0 | Submit report | 30-09-2023 1000\n");
        fileWriter.write("E | 1 | Team meeting | 10-09-2023 1400 | 10-09-2023 1600\n");
        fileWriter.close();

        // Initialize the Storage object with the test file
        storage = new Storage(testFilePath);
    }

    @Test
    public void testLoad() {
        ArrayList<Task> loadedTasks = storage.load();

        Task todo = new Todo("Buy groceries");
        todo.markAsDone();
        Task deadline = new Deadline("Submit report", "30-09-2023 1000");
        Task event = new Event("Team meeting", "10-09-2023 1400", "10-09-2023 1600");
        event.markAsDone();

        // Check if the loaded tasks match the expected tasks
        ArrayList<Task> expectedTasks = new ArrayList<>(Arrays.asList(
                todo,
                deadline,
                event
        ));

        assertEquals(expectedTasks.size(), loadedTasks.size());

        for (int i = 0; i < expectedTasks.size(); i++) {
            assertEquals(expectedTasks.get(i).toString(), loadedTasks.get(i).toString());
        }
    }

}
