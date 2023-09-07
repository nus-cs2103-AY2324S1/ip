package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.tasks.Commands;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TodoTask;




public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testPrintList() throws UnknownCommandException {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task task1 = new TodoTask("Task1");
        Task task2 = new EventTask("Task2", LocalDateTime.now(), LocalDateTime.now());
        Task task3 = new DeadlineTask("Task3", LocalDateTime.now());

        taskList.addToList(task1);
        taskList.addToList(task2);
        taskList.addToList(task3);

        Ui.printResult(Commands.LIST, null, taskList);

        assertEquals(taskList.toString().trim(), outputStreamCaptor.toString().trim());

    }
    @Test
    public void testMarkDoneAndUndone() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task task1 = new TodoTask("Task1");
        Task task2 = new EventTask("Task2", LocalDateTime.now(), LocalDateTime.now());
        Task task3 = new DeadlineTask("Task3", LocalDateTime.now());

        taskList.addToList(task1);
        taskList.addToList(task2);
        taskList.addToList(task3);

        taskList.markAsDone(2);
        taskList.markAsUnDone(2);
        taskList.markAsDone(3);

        Ui.printResult(Commands.LIST, null, taskList);

        assertEquals(taskList.toString().trim(), outputStreamCaptor.toString().trim());

    }
}
