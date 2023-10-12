package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.InvalidFileException;
import duke.main.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

 /** Test if Task is stored successfully when added, and that it can be retrieved from storage */
public class AddCommandTest {
    ToDo task = new ToDo("task");
    ToDo todo = new ToDo("tStE");
    Event event = new Event("test event", LocalDate.parse("2023-03-02"), LocalDate.parse("2023-02-02"));
    Deadline deadline = new Deadline("test event", LocalDate.parse("2023-02-02"));
    ArrayList<Task> tasks = new ArrayList<>();
    Storage storage = new Storage("testTasks.json");
    @Test
    public void addTask_toDo_addSuccessfully() {
        tasks.add(todo);
        storage.save(tasks);

        try {
            tasks = storage.load();
        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }

        assertEquals(tasks.get(tasks.size()-1), todo);
    }

    @Test
    public void addTask_event_addSuccessfully() {
        tasks.add(event);
        storage.save(tasks);

        try {
            tasks = storage.load();
        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }

        assertEquals(tasks.get(tasks.size()-1), event);
    }

    @Test
    public void addTask_deadline_addSuccessfully() {
        tasks.add(deadline);
        storage.save(tasks);

        try {
            tasks = storage.load();
        } catch (InvalidFileException e) {
            throw new RuntimeException(e);
        }

        assertEquals(tasks.get(tasks.size()-1), deadline);
    }

     @Test
     public void addTask_task_addSuccessfully() {
         tasks.add(task);
         storage.save(tasks);

         try {
             tasks = storage.load();
         } catch (InvalidFileException e) {
             throw new RuntimeException(e);
         }

         assertEquals(tasks.get(tasks.size()-1), task);
     }
}
