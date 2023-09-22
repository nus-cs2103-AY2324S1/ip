package echobot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Task;
import echobot.task.Todo;
import echobot.ui.Ui;
import javafx.scene.layout.VBox;

public class AddCommandTest {
    @Mock
    private Storage storage;
    @Mock
    private VBox dialogContainer;

    public AddCommandTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTodoTask() {
        ArrayList<Task> tasks = new ArrayList<>();

        // Mock the void method using doNothing()
        doNothing().when(storage).saveTasks(any(ArrayList.class), any(VBox.class));

        AddCommand addCommand = new AddCommand(Command.TaskType.TODO, "Attend lecture",
                null, null);
        String response = addCommand.doCommand(tasks, storage, dialogContainer);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Todo);

        // Verify the response message
        String expectedResponse = "Got it. I've added this task:\n";
        expectedResponse += "  [T] [ ] Attend lecture\n";
        expectedResponse += "Now you have 1 tasks in the list.\n";
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testAddDeadlineTask() {
        ArrayList<Task> tasks = new ArrayList<>();

        // Mock the void method using doNothing()
        doNothing().when(storage).saveTasks(any(ArrayList.class), any(VBox.class));

        AddCommand addCommand = new AddCommand(Command.TaskType.DEADLINE, "Return book",
                "2023-09-01", null);
        String response = addCommand.doCommand(tasks, storage, dialogContainer);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);

        // Verify the response message
        String expectedResponse = "Got it. I've added this task:\n";
        expectedResponse += "  [D] [ ] Return book (by: Sep 01 2023)\n";
        expectedResponse += "Now you have 1 tasks in the list.\n";
        assertEquals(expectedResponse, response);
    }
}
