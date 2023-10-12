package duke.command;

import duke.exception.InvalidFileException;
import duke.main.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    ToDo todo = new ToDo("tStE");
    Event event = new Event("test event", LocalDate.parse("2023-03-02"), LocalDate.parse("2023-02-02"));
    Deadline deadline = new Deadline("test event", LocalDate.parse("2023-02-02"));
    ArrayList<Task> beforeTasks = new ArrayList<>();
    ArrayList<Task> afterTasks = new ArrayList<>();
    Storage storage = new Storage("testTasks.json");

    private void init() {
        beforeTasks.add(todo);
        beforeTasks.add(event);
        beforeTasks.add(deadline);
        storage.save(beforeTasks);
    }

    @Test
    public void deleteTask_task_deletedSuccessfully() {
        init();
        try {
            beforeTasks = storage.load();
            beforeTasks.remove(2);

            storage.save(beforeTasks);
            afterTasks = storage.load();
        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }

        assertEquals(beforeTasks, afterTasks);
    }

}
