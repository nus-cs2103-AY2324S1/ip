package tasket.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tasket.data.TaskList;
import tasket.storage.Storage;
import tasket.ui.Ui;

public class CommandTest {

    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;

    @BeforeAll
    public static void setup() {
        try {
            Files.createFile(Path.of("./src/test/java/taskTest.txt"));

            ui = new Ui();
            storage = new Storage("./src/test/java/taskTest.txt");
            taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanup() {
        try {
            Files.deleteIfExists(Path.of("./src/test/java/taskTest.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void taskList_inputLessThanOne_exceptionThrown() {
        try {
            Command command = new MarkCommand("0");
            command.execute(taskList, ui, storage);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("The task index cannot be less than 1", e.getMessage());
        }

    }

    @Test
    public void taskList_inputLargerThanSize_exceptionThrown() {
        try {
            Command command = new MarkCommand("3");
            command.execute(taskList, ui, storage);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("The task index cannot exceed the list", e.getMessage());
        }
    }
}
