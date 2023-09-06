package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duck.Storage;
import duck.exceptions.FileIoException;

public class StorageTest {

    private Storage storage = new Storage("test.txt");
    private TaskList taskList = new TaskList();

    private File file = new File("test.txt");

    @Test
    public void givenNewSave_whenSaveCalled_thenCreateFile() throws FileIoException {
        storage.saveInFile(taskList);
        assertTrue(file.exists());
    }


    @Test
    public void givenEmptyFile_whenLoad_thenTaskListIsEmpty() throws FileIoException {
        ArrayList<Task> temp = (ArrayList<Task>) storage.load();
        assertEquals(0, temp.size());
    }
}
