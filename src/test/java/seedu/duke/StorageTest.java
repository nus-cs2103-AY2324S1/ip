package seedu.duke;

import duke.DukeException;
import duke.Storage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private static final String TEST_FILE_PATH = "test_duke.txt";

    @BeforeEach
    public void setUp() {
        // Create a test file before each test
        File testFile = new File(TEST_FILE_PATH);
        try {
            testFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete the test file after each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void saveListToTask() {
        // Create a sample task list
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDoTask("Test ToDo Task"));
        taskList.add(new DeadlineTask("Test Deadline Task", LocalDate.parse("2023-09-30")));
        taskList.add(new EventTask("Test Event Task",
                LocalDate.parse("2023-09-30"),
                LocalDate.parse("2023-10-01")));

        // Initialize the Storage object with the test file path
        Storage storage = new Storage(TEST_FILE_PATH);

        // Save the task list to the test file
        storage.saveListToDisk(taskList);

        // Read the content of the test file and verify if it matches the expected format
        try {
            Scanner scanner = new Scanner(new File(TEST_FILE_PATH));
            StringBuilder fileContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            String expectedContent = "false | Test ToDo Task | TODO \n" +
                    "false | Test Deadline Task | 2023-09-30 | DEADLINE\n" +
                    "false | Test Event Task | 2023-09-30 | 2023-10-01 | EVENT";
            assertEquals(expectedContent, fileContent.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadTest() {
        // Prepare a test file with sample tasks
        String testFileContent = "true | Sample Completed ToDo | TODO\n" +
                "false | Sample Deadline | 2023-09-30 | DEADLINE\n" +
                "false | Sample Event | 2023-09-30 | 2023-10-01 | EVENT\n";
        try {
            File testFile = new File(TEST_FILE_PATH);
            java.io.FileWriter fileWriter = new java.io.FileWriter(testFile);
            fileWriter.write(testFileContent);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize the Storage object with the test file path
        Storage storage = new Storage(TEST_FILE_PATH);

        // Load tasks from the test file
        try {

            ArrayList<Task> loadedTasks = storage.load();

            // Verify if the loaded tasks match the expected tasks
            assertEquals(3, loadedTasks.size());
            assertEquals("Sample Completed ToDo", loadedTasks.get(0).getDescription());
            assertEquals("Sample Deadline", loadedTasks.get(1).getDescription());
            assertEquals("Sample Event", loadedTasks.get(2).getDescription());
            assertSame("\u2713", loadedTasks.get(0).getStatusIcon());
            assertNotSame("\u2718", loadedTasks.get(0).getStatusIcon());
            assertNotSame("\u2718", loadedTasks.get(0).getStatusIcon());
        } catch (DukeException e) {
            e.printStackTrace();
            fail();
        }
    }

}
