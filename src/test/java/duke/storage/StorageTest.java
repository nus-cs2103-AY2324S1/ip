package duke.storage;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Tasks;
import duke.tasks.Todo;

public class StorageTest {

    @Test
    public void load_validData_success() {
        Tasks tasks = new Storage("./data/storageTest.txt").load();
        Tasks expected = this.getTestTasks();
        if (tasks.size() != expected.size()) {
            fail();
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).equals(expected.get(i))) {
                fail();
            }
        }
    }

    private Tasks getTestTasks() {
        Tasks tasks = new Tasks();

        LocalDateTime dt1 = LocalDateTime.parse("18/08/2001T19:00", DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        LocalDateTime dt2 = LocalDateTime.parse("18/09/2001T19:00", DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        LocalDateTime dt3 = LocalDateTime.parse("18/10/2001T19:00", DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        LocalDateTime dt4 = LocalDateTime.parse("18/11/2001T19:00", DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));

        tasks.add(new Todo("take a run", false));
        tasks.add(new Deadline("project report", dt1, true));
        tasks.add(new Event("project meeting", dt2, dt3, true));
        tasks.add(new Deadline("exam paper", dt4, false));

        return tasks;
    }
}
