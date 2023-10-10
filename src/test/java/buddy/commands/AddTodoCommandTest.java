package buddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class AddTodoCommandTest {
    @TempDir
    public Path testFolder;
    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_validDescription_addTodoSuccessful() {
        String validDescription = "read book";
        assertEquals("Got it. I've added this task:\n" + "[T][ ] read book\n"
                + "Now you have 1 tasks in the list.",
                new AddTodoCommand(validDescription)
                        .execute(new TaskList(), new Ui(), new Storage(getTempFilePath("cmd").toString())));
    }
}
