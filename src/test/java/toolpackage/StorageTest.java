package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.ToDos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void createStorage_noInitialFile_trueReturned() {
        try {
            Storage storage = new Storage("./testing/duke.txt");
            assertTrue(storage.createStorage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(Paths.get("./testing/duke.txt"));
                Files.deleteIfExists(Paths.get("./testing"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void createStorage_haveInitialFile_falseReturned() {
        try {
            Storage storage = new Storage("./testing/duke.txt");
            storage.createStorage();
            assertFalse(storage.createStorage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(Paths.get("./testing/duke.txt"));
                Files.deleteIfExists(Paths.get("./testing"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void saveStorage_trueReturned() {
        try {
            Storage storage = new Storage("./testing/duke.txt");
            storage.createStorage();
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            assertTrue(storage.saveStorage(tasks));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(Paths.get("./testing/duke.txt"));
                Files.deleteIfExists(Paths.get("./testing"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void saveStorage_wrongDeadlineFormat_exceptionThrown() {
        try {
            Storage storage = new Storage("./testing/duke.txt");
            storage.createStorage();
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 203-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);
        } catch (DukeException e) {
            assertEquals(" OOPS!!! Please use the proper format for the deadline (YYYY-MM-DD).", e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(Paths.get("./testing/duke.txt"));
                Files.deleteIfExists(Paths.get("./testing"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
