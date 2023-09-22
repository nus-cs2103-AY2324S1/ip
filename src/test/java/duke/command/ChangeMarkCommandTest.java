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

/** Test if Task is marked/unmarked successfully for all task types, and that it can be retrieved from storage */
public class ChangeMarkCommandTest {
    ToDo todo = new ToDo("tStE");
    Event event = new Event("test event", LocalDate.parse("2023-03-02"), LocalDate.parse("2023-02-02"));
    Deadline deadline = new Deadline("test event", LocalDate.parse("2023-02-02"));
    ArrayList<Task> beforeTasks = new ArrayList<>();
    ArrayList<Task> afterTasks = new ArrayList<>();
    Storage storage = new Storage("testTasks.json");

    private void initUnmark() {
        beforeTasks.add(todo);
        beforeTasks.add(event);
        beforeTasks.add(deadline);
        storage.save(beforeTasks);
    }

    private void initMark() {
        todo.mark();
        event.mark();
        deadline.mark();
        beforeTasks.add(todo);
        beforeTasks.add(event);
        beforeTasks.add(deadline);
        storage.save(beforeTasks);
    }

    @Test
    public void changeMark_mark_changedSuccessfully() {
        initUnmark();
        try {
            beforeTasks = storage.load();
            for (Task task : beforeTasks) {
                task.mark();
            }
            storage.save(beforeTasks);
            afterTasks = storage.load();
        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }

        assertEquals(beforeTasks, afterTasks);
    }

    @Test
    public void changeMark_unmark_changedSuccessfully() {
        initMark();
        try {
            beforeTasks = storage.load();
            for (Task task : beforeTasks) {
                task.unmark();
            }
            storage.save(beforeTasks);
            afterTasks = storage.load();
        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }

        assertEquals(beforeTasks, afterTasks);
    }
}
