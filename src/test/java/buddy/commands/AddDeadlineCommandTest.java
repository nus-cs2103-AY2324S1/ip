package buddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class AddDeadlineCommandTest {
    @TempDir
    public Path testFolder;
    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_validFields_addDeadlineSuccessful() {
        String validDescription = "return book";
        LocalDate validDate = LocalDate.parse("2023-09-09");
        assertEquals("Got it. I've added this task:\n" + "[D][ ] return book (by: 2023-09-09)\n"
                        + "Now you have 1 tasks in the list.",
                new AddDeadlineCommand(validDescription, validDate)
                        .execute(new TaskList(), new Ui(), new Storage(getTempFilePath("cmd").toString())));
    }
}
