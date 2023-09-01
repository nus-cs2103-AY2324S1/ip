package duke.utils;

import duke.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void constructTaskFromFileTest() {
        Storage storage = new Storage("data/tasks.txt");
        Task task = storage.constructTaskFromFile("[T][X] eat");
        assertEquals("eat", task.getDescription());
    }
}
