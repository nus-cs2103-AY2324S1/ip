package duke;
import org.junit.jupiter.api.Test;
import duke.task.ToDo;

import java.io.FileNotFoundException;

/**
 * This class contains JUnit test cases to verify the functionality of the Storage class.
 * It tests the saving and loading of tasks to and from a txt file.
 */
public class StorageTest {
    Storage storage = new Storage();

    @Test
    void testSaveAndLoadTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("go on a holiday"));
        try {
            storage.saveTasks("src/data/newFile.txt", tasks);
            TaskList loadedTasks = storage.loadTasks("src/data/newFile.txt");
            for (int i = 0; i < tasks.getSize(); i++) {
                assert loadedTasks.getTask(i).taskString()
                        .equals(tasks.getTask(i).taskString());
            }
            //assert storage.loadTasks("src/data/newFile.txt").equals(tasks);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
