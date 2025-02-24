package tasket.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasket.command.AddCommand;
import tasket.command.Command;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.StorageStub;
import tasket.ui.Ui;

public class TaskTest {

    private static Ui ui;
    private static StorageStub storage;
    private static TaskList taskList;

    @BeforeEach
    public void setup() {
        ui = new Ui();
        storage = new StorageStub();
        taskList = new TaskList(storage.loadWithTags());
    }

    @Test
    public void todo_ableToGetTags_success() {
        assertEquals(2, taskList.getTaskString(0).split("#", 2).length);
    }

    @Test
    public void deadline_ableToGetTags_success() {
        assertEquals(2, taskList.getTaskString(1).split("#", 2).length);
    }

    @Test
    public void event_ableToGetTags_success() {
        assertEquals(2, taskList.getTaskString(2).split("#", 2).length);
    }

    @Test
    public void deadline_ableToParseDate_success() {
        Command command = new AddCommand("deadline", "deadline return book /by 2023-09-12");
        try {
            command.execute(taskList, ui, storage);
            assertEquals("[D][ ] return book (by: 2023 Sep 12)",
                    taskList.getTaskString(taskList.size() - 1));
        } catch (TasketException e) {
            fail();
        }
    }

    @Test
    public void event_ableToParseDate_success() {
        Command command = new AddCommand("event",
                "event orientation camp /from 2023-09-12 /to 2023-09-15");
        try {
            command.execute(taskList, ui, storage);
            assertEquals("[E][ ] orientation camp (from: 2023 Sep 12 to: 2023 Sep 15)",
                    taskList.getTaskString(taskList.size() - 1));
        } catch (TasketException e) {
            fail();
        }
    }
}
