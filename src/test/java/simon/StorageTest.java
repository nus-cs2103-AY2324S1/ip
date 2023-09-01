package simon;

import org.junit.jupiter.api.Test;
import simon.task.Task;
import simon.task.ToDo;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void load_validFile_tasksLoaded() throws SimonException {
        Storage storage = new Storage("data/testFile.txt");
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void save_validTasks_tasksSaved() throws SimonException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Test Task"));
        Storage storage = new Storage("data/testSaveFile.txt");
        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(tasks.size(), loadedTasks.size());
    }
}
