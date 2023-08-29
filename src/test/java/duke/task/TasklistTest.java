package duke.task;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import duke.exception.IllegalTaskIndexException;
import duke.exception.InvalidArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddTask_ValidTodo_ShouldDisplayCorrectMessage() {
        TaskList taskList = new TaskList("sample.txt");
        assertDoesNotThrow(() ->
                taskList.addTask("todo This is a valid task"));
        String expectedOutput = "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "[T][ ] This is a valid task\n" + // Update based on your Task's toString()
                "Now you have 1 task in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
        taskList.deleteAllTasks();

    }


    @Test
    public void testAddTask_ValidEvent_ShouldDisplayCorrectMessage() {
        TaskList taskList = new TaskList("sample.txt");
        assertDoesNotThrow(() ->
                taskList.addTask("event valid event /from 2023-08-30 10:00 /to 2023-08-31 12:00"));
        String expectedOutput = "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                // Update this line based on your Event's toString()
                "[E][ ] valid event (from: 30-08-2023 10:00 to: 31-08-2023 12:00)\n" +
                "Now you have 1 task in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
        taskList.deleteAllTasks();

    }

    @Test
    public void testAddTask_ValidDeadline_ShouldDisplayCorrectMessage() {
        TaskList taskList = new TaskList("sample.txt");
        assertDoesNotThrow(() -> taskList.addTask("deadline valid deadline /by 2023-08-30 12:00"));
        String expectedOutput = "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                // Update this line based on your Deadline's toString()
                "[D][ ] valid deadline (by: 30-08-2023 12:00)\n" +
                "Now you have 1 task in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
        taskList.deleteAllTasks();
    }

    @Test
    public void testMarkAsDone_InvalidIndex_ShouldThrowException() {
        TaskList taskList = new TaskList("sample.txt");
        assertThrows(IllegalTaskIndexException.class, () -> taskList.markAsDone(1));
        taskList.deleteAllTasks();

    }

    @Test
    public void testAddTask_InvalidTaskType_ShouldThrowException() {
        TaskList taskList = new TaskList("sample.txt");
        assertThrows(InvalidArgumentException.class, () -> taskList.addTask("invalidTaskType some details"));
        taskList.deleteAllTasks();
    }


}
