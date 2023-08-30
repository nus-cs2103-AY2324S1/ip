package bruno;

import bruno.exceptions.BrunoException;
import bruno.exceptions.BrunoIncorrectFormatException;
import bruno.task.Deadline;
import bruno.task.Event;
import bruno.task.Task;
import bruno.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private String dirPath;
    private String fileName;
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    @BeforeEach
    void setUp() {
        dirPath = "data/";
        fileName = "bruno.txt";
        storage = new Storage(dirPath, fileName);
        ui = new UI();
        taskList = new TaskList(storage, ui);
    }

    @Test
    void testDirectoryExists() {
        File directory = new File(dirPath);
        assertTrue(directory.exists());
        directory.delete();
    }

    @Test
    void testWriteToFile() {
        File file = new File(dirPath + fileName);
        List<Task> list = new ArrayList<>();
        list.add(new ToDo("work"));
        TaskList.list = list;
        storage.writeToFile();
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void testWriteToFile_emptyList() {
        File file = new File(dirPath + fileName);
        List<Task> list = new ArrayList<>();
        TaskList.list = list;
        storage.writeToFile();
        assertEquals(0, file.length());
    }

    @Test
    void testLoadFile() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new ToDo("work"));
            tasks.add(new Deadline("quiz", "2023-08-29 18:00"));
            tasks.add(new Event("hackathon", "2023-08-31 18:00", "2023-09-01 18:00"));
            TaskList.list = tasks;
            storage.writeToFile();
            storage.loadFile();
            assertEquals(6, TaskList.list.size());
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testLoadFile_fileDoesNotExist() {
        try {
            storage.loadFile();
            assertEquals(0, TaskList.list.size());
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test
    void testLoadFile_invalidTaskType_exceptionThrown() {
        File file = new File(dirPath + fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("X|⭕️|work");
            storage.loadFile();
        } catch (IOException e) {
            fail();
        } catch (BrunoException e) {
            assertTrue(e instanceof BrunoIncorrectFormatException);
        }
    }

    @Test
    void testLoadFile_incorrectDateFormat_exceptionThrown() {
        File file = new File(dirPath + fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("D|⭕️|work|23-08-2023 18:00");
            storage.loadFile();
        } catch (IOException e) {
            fail();
        } catch (BrunoException e) {
            assertTrue(e instanceof BrunoIncorrectFormatException);
        }
    }

    @Test
    void testLoadFile_missingDeadline_exceptionThrown() {
        File file = new File(dirPath + fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("D|⭕️|work");
            storage.loadFile();
        } catch (IOException e) {
            fail();
        } catch (BrunoException e) {
            assertTrue(e instanceof BrunoIncorrectFormatException);
        }
    }

}
