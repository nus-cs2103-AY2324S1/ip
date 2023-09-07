package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void execute_taskNumberWithinIndex_success() {
        try{
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("read 5 books"));
            taskList.addTask(new Deadline("write 10 reviews", LocalDate.parse("2023-12-12")));
            taskList.addTask(new Event("sleep", "12am", "12pm"));
            MarkCommand markCommand = new MarkCommand(3);
            markCommand.execute(taskList, new Ui(), new Storage("data/testDuke.txt"));
            assertEquals("Nice! I've marked this task as done:\n" +
                    "[E][X] sleep (from: 12am to: 12pm)", outputStreamCaptor.toString().trim());
            assertTrue(taskList.getTask(3).isDone());
            assertEquals("T | 0 | read 5 books" + System.lineSeparator() +
                    "D | 0 | write 10 reviews | 2023-12-12" + System.lineSeparator() +
                    "E | 1 | sleep | 12am | 12pm" + System.lineSeparator(),
                    Files.readString(Path.of("data/testDuke.txt")));
        } catch (ChatException e) {
            fail("Chat Exception occurred while getting task");
        } catch (IOException e) {
            fail("IOException occurred while saving to storage");
        }
    }
    @Test
    public void execute_taskNumberOutOfIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("read 5 books"));
        taskList.addTask(new Deadline("write 10 reviews", LocalDate.parse("2023-12-12")));
        taskList.addTask(new Event("sleep", "12am", "12pm"));
        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(taskList, new Ui(), new Storage("data/testDuke.txt"));
        assertEquals("duke.exception.ChatException: ☹ OOPS!!! Please specify the correct task number.",
                outputStreamCaptor.toString().trim());
    }
}
