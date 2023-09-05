package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

public class MarkCommandTest {
    @Test
    public void testMarkCommand() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Todo("Buy groceries");
        LocalDate by = LocalDate.now();
        Task task2 = new Deadline("Finish homework", by);
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(10);
        Task task3 = new Event("Meeting", from, to);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        MarkCommand markCommand = new MarkCommand(2);
        markCommand.doCommand(tasks, new Ui(), new Storage("./data/dummy.txt"), new JTextArea());

        assertTrue(task2.isTaskDone());
    }

    @Test
    public void anotherTestMarkCommand() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Todo("Buy groceries");
        LocalDate by = LocalDate.now();
        Task task2 = new Deadline("Finish homework", by);
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(10);
        Task task3 = new Event("Meeting", from, to);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        MarkCommand markCommand = new MarkCommand(1);
        markCommand.doCommand(tasks, new Ui(), new Storage("./data/dummy.txt"), new JTextArea());

        assertFalse(task3.isTaskDone());
    }
}
