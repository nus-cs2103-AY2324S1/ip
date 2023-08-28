package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        markCommand.doCommand(tasks, new Ui(), new Storage("./data/dummy.txt"));

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
        markCommand.doCommand(tasks, new Ui(), new Storage("./data/dummy.txt"));

        assertFalse(task3.isTaskDone());
    }
}
