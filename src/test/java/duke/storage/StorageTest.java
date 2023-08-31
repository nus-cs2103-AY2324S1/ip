package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    private void notifyError() {
        fail("file should not be corrupted!");
    }

    private void notifyIO() {
        fail("IO error encountered");
    }

    private void writeBeforeTest(String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }

    @Test
    public void normalReadTest() {
        try {
            writeBeforeTest(
                    "task-list.txt",
                    "T 0 task 1" + "\n"
                    + "D 1 task 2 /by 10/12/2023 15:0" + "\n"
                    + "E 0 task 3 /from 30/8/2023 0:30 /to 30/9/2023 12:12"
            );

            ArrayList<Task> list = new ArrayList<>();
            ToDo todo = new ToDo("task 1");
            Deadline deadline = new Deadline(
                    "task 2",
                    LocalDateTime.of(2023, 12, 10, 15, 0)
            );
            deadline.markAsDone();
            Event event = new Event(
                    "task 3",
                    LocalDateTime.of(2023, 8, 30, 0, 30),
                    LocalDateTime.of(2023, 9, 30, 12, 12)
            );
            list.add(todo);
            list.add(deadline);
            list.add(event);

            assertEquals(
                    list,
                    new Storage("task-list.txt").readFromDisk()
            );
        } catch (Storage.FileCorruptedException e) {
            notifyError();
        } catch (IOException e) {
            notifyIO();
        }
    }

    @Test
    public void corruptedReadTest() {
        try {
            writeBeforeTest(
                    "task-list.txt",
                    "fnewopvnqwpiniw bjweipgjqwpjp"
            );
            assertThrows(
                    Storage.FileCorruptedException.class,
                    () -> new Storage("task-list.txt").readFromDisk()
            );

            writeBeforeTest(
                    "task-list.txt",
                    "T t task 7"
            );
            assertThrows(
                    Storage.FileCorruptedException.class,
                    () -> new Storage("task-list.txt").readFromDisk()
            );

            writeBeforeTest(
                    "task-list.txt",
                    "D 0 no end time"
            );
            assertThrows(
                    Storage.FileCorruptedException.class,
                    () -> new Storage("task-list.txt").readFromDisk()
            );
        } catch (IOException e) {
            notifyIO();
        }
    }
}
