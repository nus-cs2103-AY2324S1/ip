package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadFileTest() throws BeeException {
        Storage storage = new Storage("data/mockdata.txt");
        storage.loadTasksFromFile();
        assertEquals(4, storage.loadTasksFromFile().size());
    }

    @Test
    public void saveFileTest() throws BeeException {
        Storage storage = new Storage("data/mockdata.txt");
        storage.loadTasksFromFile();
        storage.saveTasksToFile();
        assertEquals(4, storage.loadTasksFromFile().size());
    }
}
