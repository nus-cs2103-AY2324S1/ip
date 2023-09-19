package cringebot.datafile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cringebot.exceptions.CringeBotException;
import cringebot.mocks.MockStorage;
import cringebot.tasks.TaskList;

public class StorageTest {
    @Test
    public void loadFileTest() throws CringeBotException {
        MockStorage storage = new MockStorage();
        storage.loadFromFile();
        assertEquals(0, storage.loadFromFile().size());
    }

    @Test
    public void storeFileTest() throws CringeBotException {
        MockStorage storage = new MockStorage();
        storage.writeToFile(new TaskList());
    }
}
