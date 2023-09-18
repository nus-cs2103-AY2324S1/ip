package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class DeadlineCommandTest {

    @Test
    public void deadlineCorrect(@TempDir Path savePath) throws IOException {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            FileWriter writer = new FileWriter(savePath.resolve("duke.txt").toString(), false);
            writer.write("");
            writer.close();
            TaskList taskList = storage.load();
            Command c = new DeadlineCommand("task /by 2023-08-26");
            c.execute(taskList, null, storage);
            assertEquals("[D][ ] task  (by: Aug 26 2023)", taskList.get(0).toString());

            List<String> lines = List.of("0 deadline task /by 2023-08-26");
            assertLinesMatch(lines, Files.readAllLines(savePath.resolve("duke.txt")));
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }

    @Test
    public void withoutArgument_throwsDukeException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new DeadlineCommand("").execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }

    @Test
    public void missingArgument_throwsDukeException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new DeadlineCommand("task").execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }

    @Test
    public void missingTimeArgument_throwsDukeException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new DeadlineCommand("task /by").execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }

    @Test
    public void invalidTimeArgument_throwsDukeException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new DeadlineCommand("task /by s").execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }

    @Test
    public void invalidTimeFormat_throwsDukeException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new DeadlineCommand("task /by 26-08-2023").execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }

    @Test
    public void invalidSubcommand_throwsDukeException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new DeadlineCommand("task /to 26-08-2023").execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }
}
