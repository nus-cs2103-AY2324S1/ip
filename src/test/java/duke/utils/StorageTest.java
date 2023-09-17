package duke.utils;

import duke.tasks.Event;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class StorageTest {
    @Test
    public void constructTaskFromFileTest() {
        Storage storage = new Storage("data/tasks.txt");

        Task task = storage.constructTaskFromFile("[T][X] eat");
        assertEquals("eat", task.getDescription());

        task = storage.constructTaskFromFile("[D][X] Return book (by: October 21 2023)");
        assertTrue(task.getDone());

        task = storage.constructTaskFromFile("[E][X] Do assignment " +
                "(from: September 18 2023 to: September 25 2023)");
        assertInstanceOf(Event.class, task);
    }
}
