package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

public class AddCommandTest {
    @Test
    public void testAddTodoTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/dummy.txt");

        AddCommand addCommand = new AddCommand(Command.TaskType.TODO, "Attend lecture",
                null, null);
        addCommand.doCommand(tasks, ui, storage);

        assertEquals(1, tasks.size()); // Check if the task was added to the tasks list
        assertTrue(tasks.get(0) instanceof Todo); // Check if the added task is a Todo
        assertEquals("[T][ ] Attend lecture", tasks.get(0).toString());
    }

    @Test
    public void testAddDeadlineTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/dummy.txt");

        AddCommand addCommand = new AddCommand(Command.TaskType.DEADLINE, "Return book",
                "2023-09-01", null);
        addCommand.doCommand(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);
        assertEquals("[D][ ] Return book (by: Sep 01 2023)", tasks.get(0).toString());
    }
}
