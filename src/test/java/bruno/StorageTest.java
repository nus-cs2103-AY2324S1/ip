package bruno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bruno.exceptions.BrunoException;
import bruno.exceptions.BrunoIncorrectFormatException;
import bruno.task.Deadline;
import bruno.task.Event;
import bruno.task.Task;
import bruno.task.ToDo;

public class StorageTest {

    private String dirPath;
    private String fileName;
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    @BeforeEach void setUp() {
        dirPath = "data/";
        fileName = "bruno.txt";
        storage = new Storage(dirPath, fileName);
        ui = new UI();
        taskList = new TaskList(storage, ui);
    }

    @Test void testDirectoryExists_normalCase_trueReturned() {
        File directory = new File(dirPath);
        assertTrue(directory.exists());
        directory.delete();
    }

    @Test void testWriteToFile_normalInput_writtenToFile() {
        File file = new File(dirPath + fileName);
        List<Task> list = new ArrayList<>();
        list.add(new ToDo("work"));
        taskList.setList(list);
        storage.writeToFile();
        assertTrue(file.exists());
        file.delete();
    }

    @Test void testWriteToFile_emptyList_writtenToFile() {
        File file = new File(dirPath + fileName);
        List<Task> list = new ArrayList<>();
        taskList.setList(list);
        storage.writeToFile();
        assertEquals(0, file.length());
    }

    @Test void testLoadFile_normalInput_fileLoaded() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new ToDo("work"));
            tasks.add(new Deadline("quiz", "2023-08-29 18:00"));
            tasks.add(new Event("hackathon", "2023-08-31 18:00", "2023-09-01 18:00"));
            taskList.setList(tasks);
            storage.writeToFile();
            storage.loadFile();
            assertEquals(3, taskList.getList().size());
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test void testLoadFile_fileDoesNotExist_correctOutputGenerated() {
        File file = new File(dirPath + fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            storage.loadFile();
            assertEquals(0, taskList.getList().size());
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test void testLoadFile_invalidTaskType_exceptionThrown() {
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

    @Test void testLoadFile_incorrectDateFormat_exceptionThrown() {
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

    @Test void testLoadFile_missingDeadline_exceptionThrown() {
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
