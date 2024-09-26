package glen.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @Test
    void holisticTest() {
        Storage storage = new Storage("test/tasks.txt");

        storage.addTask("T | 0 | Test Task");
        storage.addTask("D | 0 | Test Deadline | 2023-09-15");

        TaskList taskList = storage.read();

        // Validate that the tasks are read correctly from the file
        assertEquals(2, taskList.size());
        assertTrue(taskList.lst().contains("[T][ ] Test Task"));
        assertTrue(taskList.lst().contains("[D][ ] Test Deadline (by: 2023-09-15)"));

        // reset test/tasks.txt to an empty file.
        storage.updateTask(0, null);
        storage.updateTask(0, null);
    }

}
