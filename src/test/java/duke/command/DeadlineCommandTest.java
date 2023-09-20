package duke.command;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.storage.DukeStorageException;
import duke.storage.Storage;
import duke.tasks.Deadline;

public class DeadlineCommandTest {
    @Test
    public void execute_deadlineCommand_success() throws DukeStorageException, IOException {
        Command command = new DeadlineCommand(new Deadline("return book", LocalDate.parse("2023-11-30")));
        Duke duke = new Duke();
        duke.setStorage(new Storage(".duke-test-storage.txt"));
        duke.loadTasks();
        command.setDuke(duke);
        String[] response = command.execute();

        assertArrayEquals(new String[]{
                        "Got it. I've added this task:",
                        "[D][ ] return book (by: Nov 30 2023)",
                        "Now you have 1 task in the list."},
                response);

        // Cleanup
        Files.deleteIfExists(Paths.get(".duke-test-storage.txt"));
    }
}
