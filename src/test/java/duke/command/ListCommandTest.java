package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class ListCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void execute_emptyList_success() {
        ListCommand listCommand = new ListCommand();
        listCommand.execute(new TaskList(), new Ui(), new Storage("data/testDuke.txt"));
        Assertions.assertEquals("Here are the tasks in your list:", outputStreamCaptor.toString()
                .trim());
    }
    @Test
    public void execute_taskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("read 5 books"));
        taskList.addTask(new Deadline("write 10 reviews", LocalDate.parse("2023-12-12")));
        taskList.addTask(new Event("sleep", "12am", "12pm"));
        ListCommand listCommand = new ListCommand();
        listCommand.execute(taskList, new Ui(), new Storage("data/testDuke.txt"));
        Assertions.assertEquals("Here are the tasks in your list:" + System.lineSeparator() +
                "1. [T][ ] read 5 books" + System.lineSeparator() +
                "2. [D][ ] write 10 reviews (by: Dec 12 2023)" + System.lineSeparator() +
                "3. [E][ ] sleep (from: 12am to: 12pm)", outputStreamCaptor.toString().trim());
    }
}
