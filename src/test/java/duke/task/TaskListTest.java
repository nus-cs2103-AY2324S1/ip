package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.IllegalTaskIndexException;
import duke.exception.InvalidArgumentException;
import duke.ui.Ui;

public class TaskListTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private TaskList taskList;
    private Ui ui;
    @BeforeAll
    public static void setupBeforeAll() {
        deleteFileIfExists("sample.txt");
        deleteFileIfExists("sample1.txt");
        deleteFileIfExists("add_deadline.txt");
        deleteFileIfExists("remove_deadline.txt");
        deleteFileIfExists("add_event.txt");
        deleteFileIfExists("remove_event.txt");
        deleteFileIfExists("multiple_tasks.txt");
    }

    private static void deleteFileIfExists(String filename) { // This method is now static
        Path path = Paths.get("data", filename);
        try {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void markAsDone_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList("sample.txt");
        assertThrows(IllegalTaskIndexException.class, () -> taskList.markAsDone(10));
        taskList.deleteAllTasks();
    }

    @Test
    public void addTask_invalidTaskType_exceptionThrown() {
        TaskList taskList = new TaskList("sample.txt");
        assertThrows(InvalidArgumentException.class, () -> taskList.addTask("invalidTaskType some details"));
        taskList.deleteAllTasks();
    }

    @Test
    public void addTask_todoTaskType_taskAddedMessageReturned()
            throws InvalidArgumentException {
        TaskList taskList = new TaskList("sample.txt");
        String test = taskList.addTask("todo homework");
        assertEquals("Got it. I've added this task:\n"
                + "[T][ ] homework" + "\nNow you have 1 task in the list.\n", test);
    }

    @Test
    public void deleteTask_todoTaskType_taskDeletedMessageReturned()
            throws IllegalTaskIndexException, InvalidArgumentException {
        TaskList taskList = new TaskList("sample1.txt");
        taskList.addTask("todo homework");
        String test = taskList.deleteTask(1);
        assertEquals("Noted. I've removed this task:\n" + "[T][ ] homework"
                + "\nNow you have 0 task in the list.\n", test);
    }

    @Test
    public void addTask_deadlineTaskType_taskAddedMessageReturned() throws InvalidArgumentException {
        TaskList taskList = new TaskList("add_deadline.txt");
        String message = taskList.addTask("deadline submit assignment /by 30-09-2023 10:00");
        assertEquals("Got it. I've added this task:\n"
                + "[D][ ] submit assignment (by: 30-09-2023 10:00)" + "\nNow you have 1 task in the list.\n", message);
    }

    @Test
    public void deleteTask_deadlineTaskType_taskDeletedMessageReturned()
            throws IllegalTaskIndexException, InvalidArgumentException {
        TaskList taskList = new TaskList("remove_deadline.txt");
        taskList.addTask("deadline submit assignment /by 30-09-2023 10:00");
        String message = taskList.deleteTask(1);
        assertEquals("Noted. I've removed this task:\n"
                + "[D][ ] submit assignment (by: 30-09-2023 10:00)" + "\nNow you have 0 task in the list.\n", message);
    }

    @Test
    public void addTask_eventTaskType_taskAddedMessageReturned() throws InvalidArgumentException {
        TaskList taskList = new TaskList("add_event.txt");
        String message = taskList.addTask("event conference /from 01-10-2023 09:00 /to 01-10-2023 17:00");
        assertEquals("Got it. I've added this task:\n"
                + "[E][ ] conference (from: 01-10-2023 09:00 to: 01-10-2023 17:00)"
                + "\nNow you have 1 task in the list.\n", message);
    }

    @Test
    public void deleteTask_eventTaskType_taskDeletedMessageReturned()
            throws IllegalTaskIndexException, InvalidArgumentException {
        TaskList taskList = new TaskList("remove_event.txt");
        taskList.addTask("event conference /from 01-10-2023 09:00 /to 01-10-2023 17:00");
        String message = taskList.deleteTask(1);
        assertEquals("Noted. I've removed this task:\n"
                + "[E][ ] conference (from: 01-10-2023 09:00 to: 01-10-2023 17:00)"
                + "\nNow you have 0 task in the list.\n", message);
    }

    @Test
    public void listTasks_multipleTaskTypes_correctListReturned() throws InvalidArgumentException {
        TaskList taskList = new TaskList("multiple_tasks.txt");
        taskList.addTask("todo homework");
        taskList.addTask("deadline submit assignment /by 30-09-2023 10:00");
        taskList.addTask("event conference /from 01-10-2023 09:00 /to 01-10-2023 17:00");
        String tasks = taskList.listTasks();
        String expected =
                "Here are the tasks in your list:\n"
                        + "1.[T][ ] homework\n"
                        + "2.[D][ ] submit assignment (by: 30-09-2023 10:00)\n"
                        + "3.[E][ ] conference (from: 01-10-2023 09:00 to: 01-10-2023 17:00)\n";
        assertEquals(expected, tasks);
    }
}
