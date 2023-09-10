package geraldbot.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;


public class TaskStorageTest {

    @Test
    public void read_emptyFile_emptyTaskList() {
        TaskStorage taskStorage = initializeStorage();
        ArrayList<Task> taskList = taskStorage.read();
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void read_existingFile_correctTaskList() {
        TaskStorage taskStorage = initializeStorage();

        ArrayList<String> testData = new ArrayList<>();
        testData.add("T | 1 | Buy groceries");
        testData.add("D | 0 | Submit report | 2023-08-31T18:00");
        testData.add("E | 0 | Team meeting | 2023-08-27T14:00-16:00");

        try {
            TaskStorageTest.writeTestDataToFile(taskStorage, testData);
        } catch (IOException e) {
            fail("Failed to write test data to file");
        }

        ArrayList<Task> taskList = taskStorage.read();
        assertEquals(3, taskList.size());
        assertTrue(taskList.get(0) instanceof Todo);
        assertTrue(taskList.get(1) instanceof Deadline);
        assertTrue(taskList.get(2) instanceof Event);
    }

    @Test
    public void addTask_validTask_successfullyAdded() {
        TaskStorage storage = initializeStorage();

        Task newTask = new Todo("Read a book", false);
        String fileFormat = newTask.fileFormat();

        storage.addTask(fileFormat);

        ArrayList<Task> taskList = storage.read();
        assertEquals(1, taskList.size());
    }

    @Test
    public void updateTask_validIndex_updatedSuccessfully() {
        TaskStorage taskStorage = initializeStorage();

        ArrayList<String> testData = new ArrayList<>();
        testData.add("T | 0 | Buy groceries");

        try {
            TaskStorageTest.writeTestDataToFile(taskStorage, testData);
        } catch (IOException e) {
            fail("Failed to write test data to file");
        }

        String updatedTaskFileFormat = "T | 1 | Buy groceries";
        taskStorage.updateTask(0, updatedTaskFileFormat);

        ArrayList<Task> taskList = taskStorage.read();
        assertEquals(1, taskList.size());
        assertEquals("X", taskList.get(0).getStatusIcon());
    }

    private TaskStorage initializeStorage() {
        String testFilePath = "./testData/testStorageData.txt";
        TaskStorage storage = new TaskStorage(testFilePath);
        File file = storage.getFile();
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return storage;
    }

    private static void writeTestDataToFile(TaskStorage taskStorage, ArrayList<String> testData) throws IOException {
        File testFile = new File(taskStorage.getFile().getPath());
        if (!testFile.exists()) {
            testFile.getParentFile().mkdirs();
            testFile.createNewFile();
        }

        FileWriter fw = new FileWriter(testFile);
        fw.write(String.join("\n", testData));
        fw.close();
    }
}
