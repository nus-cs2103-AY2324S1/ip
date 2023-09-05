package duke.test;
import duke.Storage;
import org.junit.jupiter.api.Test;
import duke.TaskList;
import duke.task.ToDo;

import java.io.FileNotFoundException;

public class StorageTest {
    Storage storage = new Storage();

    @Test
    //This test checks whether the files can be created locally on computers
    //and whether the tasks can be saved on the file successfully
    //and can be loaded successfully
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
