package crusader;

import crusader.exception.CrusaderException;
import crusader.exception.CrusaderParseException;
import crusader.task.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    private static final String TEST_PATH = "./data/test.txt";

    @BeforeEach
    public void setup() {
        File testFile = new File(TEST_PATH);
        try (PrintWriter fileWriter = new PrintWriter(testFile)) {
            fileWriter.println("E|eat water|X|01/12/2008 12|12/12/2023 14");
            fileWriter.println("T|CS2100| \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void setdown() {
        File testFile = new File(TEST_PATH);
        testFile.delete();
    }

    @Test
    public void loadTasks_correctFormat_success() throws CrusaderException {
        ArrayList<Task> taskList = new Storage(TEST_PATH).loadTasks();
        assertEquals("[E][X] eat water (from: Dec 01 2008 12 to: Dec 12 2023 14)",taskList.get(0).toString());
        assertEquals("[T][ ] CS2100",taskList.get(1).toString());
    }

    @Test
    public void loadTasks_incorrectFormat_ExceptionThrown() {
        File testFile = new File(TEST_PATH);
        try (PrintWriter fileWriter = new PrintWriter(testFile)) {
            fileWriter.println("E|eat water|X|01/12/2008 12|december 12");
            fileWriter.println("T|CS2100| | |33\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThrows(CrusaderParseException.class, () -> new Storage(TEST_PATH).loadTasks());
    }

}
