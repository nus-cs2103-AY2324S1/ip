package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;




/**
 * This class is used to test the TaskList class
 */
public class TaskListTester {
    /**
     * The path of the test file
     */
    public static final String TEST_PATH = "./data/dukeTest.txt";
    /**
     * The storage object used for testing
     */
    private final Storage testStorage = Storage.createStorage(TEST_PATH);

    /**
    * Testing the addTask method for valid input
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
     * Testing the delete Task method for invalid input
     * @throws WrongInputException  if the input is invalid
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

    /**
     * Testing the parseTask method based on valid inputs
     * @throws WrongInputException if the input is invalid however should not occur at all
     */
    @Test
    public void parseTask_validInput_success() {
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

            File file = new File(TEST_PATH);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task scannedTask = TaskList.parseTask(data);
                assertEquals(scannedTask.toString(), taskList.getTask(i));
                i++;
            }
        } catch (WrongInputException e) {
            Assertions.fail();
        } catch (FileNotFoundException e) {
            Assertions.fail();
        }
    }
}
