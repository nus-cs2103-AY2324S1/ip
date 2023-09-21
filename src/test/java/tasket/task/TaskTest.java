package tasket.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasket.command.AddCommand;
import tasket.command.Command;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

public class TaskTest {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setup() {
        try {
            Files.createFile(Path.of("./src/test/java/taskTest.txt"));

            ui = new Ui();
            storage = new Storage("./src/test/java/taskTest.txt");
            taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void cleanup() {
        try {
            Files.deleteIfExists(Path.of("./src/test/java/taskTest.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deadline_ableToParseDate_success() {
        Command command = new AddCommand("deadline", "deadline return book /by 2023-09-12");
        try {
            command.execute(taskList, ui, storage);
            Assertions.assertEquals("[D][ ] return book (by: 2023 Sep 12)", taskList.getTaskString(0));
        } catch (TasketException e) {
            Assertions.fail();
        }
    }

    @Test
    public void event_ableToParseDate_success() {
        Command command = new AddCommand("event",
                "event orientation camp /from 2023-09-12 /to 2023-09-15");
        try {
            command.execute(taskList, ui, storage);
            Assertions.assertEquals("[E][ ] orientation camp (from: 2023 Sep 12 to: 2023 Sep 15)",
                    taskList.getTaskString(0));
        } catch (TasketException e) {
            Assertions.fail();
        }
    }

}
