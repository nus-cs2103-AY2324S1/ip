package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTester {
        private final static String TEST_PATH = "./data/dukeTest.txt";
        private final Storage testStorage = Storage.createStorage(TEST_PATH);
        /**
        * Testing the addTask method
        */
        @Test
        public void addTask_validInput_success() {
            // Always clear file to start a-fresh
            testStorage.clearFile();
            TaskList taskList = new TaskList(TEST_PATH);
            Task task = new ToDos("test");
            taskList.addTask(task, 1, testStorage);
            assertEquals(taskList.getTaskObject(0), task);
        }

        /**
        * Testing the deleteTask method
        */
        @Test
        public void deleteTask_validInput_success() {
            // Always clear file to start a-fresh
            testStorage.clearFile();
            TaskList taskList = new TaskList(TEST_PATH);
            Task task = new ToDos("test");
            taskList.addTask(task, 0, testStorage);
            taskList.deleteTask(0, 1, testStorage);
            assertEquals(taskList.length(), 0);
        }

        @Test
        public void parseTask_validInput_success() throws WrongInputTask {
           try {
               // Always clear file to start a-fresh
               testStorage.clearFile();
               TaskList taskList = new TaskList(TEST_PATH);
               Task task = new ToDos("Yoop");
               Task deadline = new Deadline("weewoop", DateTimeStub.createDateTime("25/08/2023 1800"));
               Task event = new Event("test", DateTimeStub.createDateTime("25/08/2023 1800"),
                       DateTimeStub.createDateTime("25/08/2023 1900"));
               taskList.addTask(task, 0, testStorage);
               taskList.addTask(deadline, 1, testStorage);
               taskList.addTask(event, 2, testStorage);
               TaskList taskListAfterSave = new TaskList(TEST_PATH);

               File file = new File(TEST_PATH);
               Scanner scanner = new Scanner(file);
               int i = 0;
               while (scanner.hasNextLine()) {
                   String data = scanner.nextLine();
                   Task scannedTask = TaskList.parseTask(data);
                   assertEquals(scannedTask.toString(), taskList.getTask(i));
                   i++;
               }


           } catch (WrongInputTask e) {
               Assertions.fail();
           } catch (FileNotFoundException e) {
                Assertions.fail();
           }
        }

        @Test
        public void parseTask_corruptedData_exceptionThrown() {
            String corruptedData3 = "E | false | test | WAug-25-2023 1900 PM | Aug-35-2023 9999 PM";
            try {
                TaskList.parseTask(corruptedData3);
                Assertions.fail();
            } catch (WrongInputTask e) {
                assertEquals("Invalid Format: Stored event is invalid / corrupted\n" +
                                "Recommendation: Please clear the folder and restart the program",
                        e.getMessage());
            }
        }




}
