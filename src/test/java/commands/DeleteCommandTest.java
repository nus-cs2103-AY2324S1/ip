package commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import functions.TaskList;
import functions.Ui;
import functions.Storage;
import java.io.IOException;

public class DeleteCommandTest {

    @Test
    public void testExecute() {
        // Create a TaskList with some tasks for testing
        TaskList tasks = new TaskList();
        tasks.createTask("Task 1", null, null, null);
        tasks.createTask("Task 2", null, null, null);
        tasks.createTask("Task 3", null, null, null);

        Ui ui = new Ui();
        Storage storage = new Storage("data.txt");

        // Create a DeleteCommand with a task number
        DeleteCommand deleteCommand = new DeleteCommand(2);

        try {
            String result = deleteCommand.execute(tasks, ui, storage);

            // Assert that the result matches the expected output
            assertEquals("Noted. I've removed this task:\n" +
                    "  [T][ ] Task 2\n" +
                    "Now you have 2 tasks in the list.\n", result);

            // Assert that the TaskList has one less task after deletion
            assertEquals(2, tasks.numOfTasks());
        } catch (IOException e) {
            fail("IOException should not be thrown during testing.");
        }
    }
}
