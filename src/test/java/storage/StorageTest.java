package storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import taskutil.TaskList;

public class StorageTest {
    Storage storage = new Storage("./data");

    @Test
    public void loadData_openFile_success() {
        assertTrue(storage.loadData(new TaskList()));
    }
}
