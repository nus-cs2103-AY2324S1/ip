package duke;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testPrintFileContents() {
        // Clear the test file
        clearTestFile();

        // Add a test task to the file
        ToDo task1 = new ToDo("Test task 1");
        taskList.addToList(task1, 0);

        // Check if the printed content matches the task
        String expected = "1.[T][ ] Test task 1\n";
        assertEquals(expected, taskList.printFileContents());
    }

    @Test
    public void testAddToList() {
        // Clear the test file
        clearTestFile();

        // Add a task to the list
        Task task = new ToDo("Test task 2");
        String response = taskList.addToList(task, 0);

        // Check if the response is correct
        String expectedResponse = "Got it! I've added this task:\n[T][ ] Test task 2\nYou now have 1 task(s) in the list";
        assertEquals(expectedResponse, response);

        // Check if the task was added to the list and saved to the file
        String fileContents = readFileContents();
        assertTrue(fileContents.contains("[T][ ] Test task 2"));
    }

    @Test
    public void testDelete() {
        // Clear the test file
        clearTestFile();

        // Add two tasks to the list
        ToDo task1 = new ToDo("Test task 1");
        ToDo task2 = new ToDo("Test task 2");
        taskList.addToList(task1, 1);
        taskList.addToList(task2, 2);

        // Delete the second task
        String response = taskList.delete("delete 2");

        // Check if the response is correct
        String expectedResponse = "Got it! I've removed this task:\n[T][ ] Test task 2\nYou now have 1 task(s) in the list";
        assertEquals(expectedResponse, response);

        // Check if the second task was removed from the list and the file
        String fileContents = readFileContents();
        assertFalse(fileContents.contains("[T][ ] Test task 2"));
    }

    @Test
    public void testMark() {
        // Clear the test file
        clearTestFile();

        // Add a task to the list
        Task task = new ToDo("Test task 1");
        taskList.addToList(task, 0);

        // Mark the task as done
        String response = taskList.mark("mark 1");

        // Check if the response is correct
        String expectedResponse = "Great job! You've completed the following task:\n[T][X] Test task 1";
        assertEquals(expectedResponse, response);

        // Check if the task was marked as done in the list and saved to the file
        String fileContents = readFileContents();
        assertTrue(fileContents.contains("[T][X] Test task 1"));
    }

    @Test
    public void testUnMark() {
        // Clear the test file
        clearTestFile();

        // Add a task to the list and mark it as done
        Task task = new ToDo("Test task 1");
        taskList.addToList(task, 0);
        taskList.mark("mark 1");

        // Unmark the task as done
        String response = taskList.unMark("unmark 1");

        // Check if the response is correct
        String expectedResponse = "You've marked the following task as incomplete:\n[T][ ] Test task 1";
        assertEquals(expectedResponse, response);

        // Check if the task was unmarked as done in the list and saved to the file
        String fileContents = readFileContents();
        assertTrue(fileContents.contains("[T][ ] Test task 1"));
    }

    // Helper method to clear the test file
    private void clearTestFile() {
        try {
            FileWriter fw = new FileWriter(taskList.getFile().getPath(), false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to read the contents of the test file
    private String readFileContents() {
        try {
            Scanner s = new Scanner(taskList.getFile());
            StringBuilder stringBuilder = new StringBuilder();

            while (s.hasNext()) {
                stringBuilder.append(s.nextLine()).append(System.lineSeparator());
            }

            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}
