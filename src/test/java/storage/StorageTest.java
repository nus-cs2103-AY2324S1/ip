package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskutil.TaskList;

public class StorageTest {
    private final Storage storage = new Storage("./data");

    @Test
    public void loadData_openFile_success() {
        Assertions.assertTrue(storage.loadData(new TaskList()));
    }
}
