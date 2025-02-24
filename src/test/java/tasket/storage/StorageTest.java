package tasket.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasket.data.TaskList;
import tasket.task.Event;
import tasket.task.Task;

public class StorageTest {
    private static Storage storage;
    private static final String FILEPATH = "data/taskTest.txt";
    @BeforeEach
    public void setup() {
        storage = new Storage(FILEPATH);
        try {
            Files.createDirectories(Path.of(FILEPATH.substring(0, FILEPATH.lastIndexOf("/"))));
            Files.createFile(Path.of(FILEPATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeTasks() {
        FileWriter fw = null;
        try {
            try {
                fw = new FileWriter(FILEPATH);

                fw.write("T | 0 | read book #study" + System.lineSeparator());
                fw.write("D | 0 | return book | Sun 2pm #library" + System.lineSeparator());
                fw.write("E | 0 | project meeting | Sun 8pm | 10pm #library" + System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fw != null) {
                    fw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeTasksCorrupted() {
        FileWriter fw = null;
        try {
            try {
                fw = new FileWriter(FILEPATH);

                fw.write("T | 0 | read book #study" + System.lineSeparator());
                fw.write("D | 0 | return book Sun 2pm #library" + System.lineSeparator()); // Corrupted task
                fw.write("E | 0 | project meeting | Sun 8pm | 10pm #library" + System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fw != null) {
                    fw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void cleanup() {
        try {
            Files.deleteIfExists(Path.of(FILEPATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void storage_loadFile_success() {
        writeTasks();
        try {
            assertEquals(3, storage.load().size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storage_loadCorruptedFile_success() {
        writeTasksCorrupted();
        try {
            assertEquals(2, storage.load().size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storage_appendTask_success() {
        Task task = new Event("project meeting", "Sun 8pm", "10pm");
        try {
            storage.append(task.toSaveString());
            String actualString = storage.load().get(0).toSaveString();
            assertEquals(task.toSaveString(), actualString);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storage_rewriteTask_success() {
        try {
            writeTasks();
            TaskList taskList = new TaskList(storage.load());
            taskList.mark(0);
            taskList.remove(1);

            storage.rewriteSaveFile(taskList);

            TaskList newTaskList = new TaskList(storage.load());

            assertEquals(2, newTaskList.size());
            assertEquals("T | 1 | read book #study", newTaskList.getTaskSaveString(0));
        } catch (Exception e) {
            fail();
        }

    }
}
