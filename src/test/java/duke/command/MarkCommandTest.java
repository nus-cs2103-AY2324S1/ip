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
    @Test
    public void execute_taskNumberWithinIndex_success() {
        try{
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("read 5 books"));
            taskList.addTask(new Deadline("write 10 reviews", LocalDate.parse("2023-12-12")));
            taskList.addTask(new Event("sleep", "12am", "12pm"));
            String[] taskToMark = {"1", "3"};
            MarkCommand markCommand = new MarkCommand(taskToMark);
            String result = markCommand.execute(taskList, new Ui(), new Storage("data/testDuke.txt"));
            assertEquals("Nice! I've marked the following task(s) as done:"
                    + System.lineSeparator()
                    + "  [T][X] read 5 books"
                    + System.lineSeparator()
                    + "  [E][X] sleep (from: 12am to: 12pm)", result);
            assertTrue(taskList.getTask(1).isDone() && taskList.getTask(3).isDone());
            assertEquals("T | 1 | read 5 books" + System.lineSeparator()
                            + "D | 0 | write 10 reviews | 2023-12-12" + System.lineSeparator()
                            + "E | 1 | sleep | 12am | 12pm" + System.lineSeparator(),
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
        MarkCommand markCommand = new MarkCommand(new String[] {"0"});
        String result = markCommand.execute(taskList, new Ui(), new Storage("data/testDuke.txt"));
        assertEquals("OOPS!!! Please specify the correct task number.", result);
    }
}
