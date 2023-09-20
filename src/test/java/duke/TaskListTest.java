package duke;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.getFile().deleteOnExit();
    }

    @Test
    public void testMark() {

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
