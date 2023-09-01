package duke.util;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Storage Class used in CheeChat
 */
public class StorageTest {


    /**
     * Tests whether an empty ArrayList will be returned by the load method in the Storage class
     * when the file inserted is empty.
     */
    @Test
    public void load_emptyFile_emptyArrayListReturned() {
        Storage storage = new Storage("./data/empty.txt");
        ArrayList<Task> tasks = storage.load();
        int size = tasks.size();
        assertEquals(0, size);
    }
}