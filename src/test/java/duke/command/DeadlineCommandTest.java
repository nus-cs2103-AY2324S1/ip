package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            TaskList taskList = storage.load();
            Command c = new DeadlineCommand("task /by 2023-08-26");
            c.execute(taskList, null, storage);
            assertEquals("[D][ ] task  (by: Aug 26 2023)", taskList.get(0).toString());

            List<String> lines = List.of("0 deadline task /by 2023-08-26");
            assertLinesMatch(lines, Files.readAllLines(savePath.resolve("duke.txt")));
        } catch (DukeException ignored) {
            // Do Nothing
        }
    }

    @Test
    public void withoutArgument_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new DeadlineCommand("").execute(null, null, null);
        });
    }

    @Test
    public void missingArgument_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new DeadlineCommand("task").execute(null, null, null);
        });
    }

    @Test
    public void missingTimeArgument_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new DeadlineCommand("task /by").execute(null, null, null);
        });
    }

    @Test
    public void invalidTimeArgument_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new DeadlineCommand("task /by s").execute(null, null, null);
        });
    }

    @Test
    public void invalidTimeFormat_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new DeadlineCommand("task /by 26-08-2023").execute(null, null, null);
        });
    }

    @Test
    public void invalidSubcommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new DeadlineCommand("task /to 26-08-2023").execute(null, null, null);
        });
    }
}
