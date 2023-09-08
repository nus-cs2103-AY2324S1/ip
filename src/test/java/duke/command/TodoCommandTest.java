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

public class TodoCommandTest {

    @Test
    public void todoCorrect(@TempDir Path savePath) throws IOException {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            FileWriter writer = new FileWriter(savePath.resolve("duke.txt").toString(), false);
            writer.write("");
            writer.close();
            TaskList taskList = storage.load();
            Command c = new TodoCommand("task");
            c.execute(taskList, null, storage);
            assertEquals("[T][ ] task", taskList.get(0).toString());

            List<String> lines = List.of("0 todo task");
            assertLinesMatch(lines, Files.readAllLines(savePath.resolve("duke.txt")));
        } catch (DukeException ignored) {
            // Do Nothing
        }
    }

    @Test
    public void missingArgument_throwsException(@TempDir Path savePath) {
        try {
            Storage storage = new Storage(savePath.resolve("duke.txt").toString());
            TaskList taskList = storage.load();
            assertThrows(DukeException.class, () -> {
                new TodoCommand(null).execute(taskList, null, storage);
            });
        } catch (DukeException e) {
            fail("DukeException Thrown");
        }
    }
}
