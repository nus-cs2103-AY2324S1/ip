package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import duke.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import duke.task.Task;
import duke.task.*;


class StorageTest {

    @Test
    void load_testDataTextFile_correctArrayOfStrings() {
        try {
            Storage s = new Storage("src/TestData.txt");
            ArrayList<String> array = s.load();
            assertEquals("T/0/homework",array.get(0));
            assertEquals("D/1/speech script/2023-09-02T04:00",array.get(1));
            assertEquals("E/0/project meeting/2023-12-02T04:00/2023-12-02T06:00",array.get(2));
        } catch (RichieException e) {
            fail("Exception should not be thrown by valid inputs from data source");
        }

    }

    @Test
    void saveCurrentTask_setOfTasks_correctSavedData() {
        try {
            String filePath = "src/testData2.txt";
            Storage s = new Storage(filePath);
            LocalDateTime dateTime = LocalDateTime.parse("2012-02-15T12:30");
            Todo task1 = new Todo("Do Homework");
            Deadline task2 = new Deadline("Walk Dog", dateTime);
            Event task3 = new Event("Project Meeting", dateTime, dateTime);
            ArrayList<Task> testTasksArrayList = new ArrayList<>();
            testTasksArrayList.add(task1);
            testTasksArrayList.add(task2);
            testTasksArrayList.add(task3);

            s.saveCurrentTasks(testTasksArrayList);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            ArrayList<String> newlySavedData = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                newlySavedData.add(line);
            }

            ArrayList<String> testArrayList = new ArrayList<>();
            testArrayList.add("T/0/Do Homework");
            testArrayList.add("D/0/Walk Dog/2012-02-15T12:30");
            testArrayList.add("E/0/Project Meeting/2012-02-15T12:30/2012-02-15T12:30");
            assertIterableEquals(testArrayList, newlySavedData);
        } catch (IOException e) {
            fail("Exception should not be thrown for saving correct set of tasks, this is the error message : " + e.getMessage());
        }
    }
}