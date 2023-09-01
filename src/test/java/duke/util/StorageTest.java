package duke.util;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void load_emptyFile_emptyArrayListReturned() {
        Storage storage = new Storage("./data/empty.txt");
        ArrayList<Task> tasks = storage.load();
        int size = tasks.size();
        assertEquals(0, size);
    }
}