package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

public class UnmarkCommandTest {
    @Test
    public void unmarkCorrect(@TempDir Path savePath) throws IOException {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            FileWriter writer = new FileWriter(savePath.resolve("duke.txt").toString(), false);
            writer.write("1 todo task\n0 todo task1\n");
            writer.close();
            TaskList taskList = storage.load();
            Command c = new UnmarkCommand("1");
            c.execute(taskList, null, storage);
            assertEquals(" ", taskList.get(0).getStatusIcon());

            List<String> lines = List.of("0 todo task", "0 todo task1");
            assertLinesMatch(lines, Files.readAllLines(savePath.resolve("duke.txt")));
        } catch (DukeException ignored) {
            // Do Nothing
        }
    }

    @Test
    public void withoutArgument_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new UnmarkCommand("").execute(null, null, null);
        });
    }

    @Test
    public void nonNumeric_throwsDukeException() {
        assertThrows(DukeException.class, () -> {
            new UnmarkCommand("notNumber").execute(null, null, null);
        });
    }

    @Test
    public void invalidNumericNumber_throwsDukeException(@TempDir Path savePath) throws IOException {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            FileWriter writer = new FileWriter(savePath.resolve("duke.txt").toString(), false);
            writer.write("0 todo task\n0 todo task1\n");
            writer.close();
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new UnmarkCommand("3").execute(taskList, null, storage);
            });
        } catch (DukeException ignored) {
            // Do Nothing
        }
    }
}
