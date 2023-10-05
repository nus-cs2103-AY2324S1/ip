package buddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class AddEventCommandTest {
    @TempDir
    public Path testFolder;
    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_validFields_addEventSuccessful() {
        String validDescription = "family trip";
        LocalDate validStartDate = LocalDate.parse("2023-12-19");
        LocalDate validEndDate = LocalDate.parse("2023-12-29");
        assertEquals("Got it. I've added this task:\n"
                        + "[E][ ] family trip (from: 2023-12-19 to: 2023-12-29)\n"
                        + "Now you have 1 tasks in the list.",
                new AddEventCommand(validDescription, validStartDate, validEndDate)
                        .execute(new TaskList(), new Ui(), new Storage(getTempFilePath("cmd").toString())));
    }
}
