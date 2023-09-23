package haste.data;

import haste.commands.Parser;
import haste.tasks.Task;
import haste.tasks.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;

public class StorageTest {
    @Test
    public void readTest() {
        Storage store = new Storage("./Test.txt");
        String line = "E|false|project meeting|2023-02-21 1600|2023-02-21 1700";
        Task event = store.readTask(line);
        LocalDateTime start = Parser.parseTime("2023-02-21 1600");
        LocalDateTime end = Parser.parseTime("2023-02-21 1700");
        Task actualEvent = new Event("project meeting", start, end, false);
        Assertions.assertEquals(event.toString(), actualEvent.toString());
        store.delete();
    }

    @Test
    public void createTest() {
        String path = "./Test.txt";
        File file = new File(path);
        assertFalse(file.exists());
        Storage store = new Storage("./Test.txt");
        assertTrue(file.exists());

    }
}
