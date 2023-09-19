package cringebot.datafile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cringebot.exceptions.CringeBotException;
import cringebot.tasks.TaskList;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadFileTest() throws CringeBotException {
        Storage storage = new Storage("./src/test/resources/data/MockData.ser");
        storage.loadFromFile();
        assertEquals(0, storage.loadFromFile().size());
    }

    @Test
    public void storeFileTest() throws CringeBotException {
        Storage storage = new Storage("./src/test/resources/data/MockData.ser");
        storage.writeToFile(new TaskList());
    }
}
