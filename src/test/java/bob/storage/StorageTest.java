package bob.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bob.data.exception.DukeException;
import bob.data.task.DeadlineTask;
import bob.data.task.EventTask;
import bob.data.task.Task;
import bob.data.task.ToDoTask;


public class StorageTest {
    @Test
    public void storage_fileExists_existingFileUsed() {
        File taskFile = new File("./test.txt");
        try {
            taskFile.createNewFile();
            assertFalse(taskFile.createNewFile());
        } catch (IOException e) {
            System.out.println("Unable to create file.");
            e.printStackTrace();
        }
    }
//
//    @Test
//    public void storage_fileDoesntExist_newFileCreated() {
//        File taskFile = new File("./new.txt");
//        try {
//            assertTrue(taskFile.createNewFile());
//        } catch (IOException e) {
//            System.out.println("Unable to create file.");
//            e.printStackTrace();
//        }
//    }

    @Test
    public void readFromFile_goodFile_storedTasksAddedToList() {
        try {
            ArrayList<Task> testList = new ArrayList<>();
            ToDoTask toDoTest = new ToDoTask("todotest");
            ToDoTask toDoTest2 = new ToDoTask("todotest2");
            toDoTest2.setDone();
            DeadlineTask deadlineTest = new DeadlineTask("deadlinetest", "12/12/2024 1200");
            DeadlineTask deadlineTest2 = new DeadlineTask("deadlinetest2", "12/12/2024 1200");
            deadlineTest2.setDone();
            EventTask eventTest = new EventTask(
                    "eventtest",
                    "12/12/2024 1200",
                    "13/12/2024 1200");
            EventTask eventTest2 = new EventTask(
                    "eventtest2",
                    "12/12/2024 1200",
                    "13/12/2024 1200");
            eventTest2.setDone();

            testList.add(toDoTest);
            testList.add(toDoTest2);
            testList.add(deadlineTest);
            testList.add(deadlineTest2);
            testList.add(eventTest);
            testList.add(eventTest2);

            FileWriter writer = new FileWriter("test.txt");
            writer.write("Todo,0,todotest\n"
                            + "Todo,1,todotest2\n"
                            + "Deadline,0,deadlinetest,12/12/2024 1200\n"
                            + "Deadline,1,deadlinetest2,12/12/2024 1200\n"
                            + "Event,0,eventtest,12/12/2024 1200,13/12/2024 1200\n"
                            + "Event,1,eventtest2,12/12/2024 1200,13/12/2024 1200\n");
            writer.close();

            Storage storage = new Storage();
            File taskFile = new File("./test.txt");
            storage.setFile(taskFile);
            ArrayList<Task> outputList = new ArrayList<>();
            storage.readFromFile(outputList);
            assertEquals(testList, outputList);
        } catch (DukeException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void readFromFile_badFile_exceptionThrown() {
        try {
            FileWriter writer = new FileWriter("test.txt");
            writer.write("Todo1,0,todotest\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }

        Storage storage = new Storage();
        File taskFile = new File("./test.txt");
        storage.setFile(taskFile);
        ArrayList<Task> output = new ArrayList<>();
        DukeException thrown = assertThrows(DukeException.class, () -> storage.readFromFile(output),
                "Expected readFromFile to throw but it did not.");
        assertTrue(thrown.getMessage().contains("Corrupt file."));
    }
}
