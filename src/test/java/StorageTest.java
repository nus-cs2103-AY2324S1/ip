import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import chatter.Storage;
import chatter.task.Deadline;
import chatter.task.Task;
import chatter.task.ToDo;

public class StorageTest {
    /**
     * Comparison function to compare the contents of two ArrayLists.
     *
     * @param first First arraylist.
     * @param second Second arraylist.
     * @return Boolean determining if they have the same content.
     */
    public boolean arrayListComparison(ArrayList<Task> first, ArrayList<Task> second) {
        for (int i = 0; i < first.size(); i++) {
            if (!(first.get(i).toString()).equals(second.get(i).toString())) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void storageReadFile_randomTasks_listMatch() throws IOException {
        try {
            Storage storage = new Storage("./data/chatter.txt");
            storage.saveFile("T, false, return book\nD, true, help, tmr");
            ArrayList<Task> result = new ArrayList<>();
            result.add(new ToDo("return book"));
            Task t = new Deadline("help", "tmr");
            t.markAsDone();
            result.add(t);
            assertTrue(arrayListComparison(result, storage.readFile()));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void storageReadFile_newStorageObjectWithTasks_storageFileCreated() throws IOException {
        Path path = Path.of("./data/chatter.txt");

        if (Files.exists(path)) {
            Files.delete(path);
        }

        Storage storage = new Storage("./data/chatter.txt");
        storage.saveFile("T, false, return book");
        ArrayList<Task> result = new ArrayList<>();
        result.add(new ToDo("return book"));
        assertTrue(arrayListComparison(result, storage.readFile()));
    }
}
