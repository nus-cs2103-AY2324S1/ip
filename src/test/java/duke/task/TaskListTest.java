package duke.task;

import duke.filemanagement.Storage;
import duke.userio.Parser;
import duke.userio.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void loadFileToTaskList(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/data/test.txt");
        storage.loadFileToTaskManager(taskList);
        assertEquals("1.[T][ ] Borrow Book\n" +
                "2.[E][ ] Read Book (from: today to: next Sunday)\n" +
                "3.[T][ ] Test\n", taskList.outputNumberedList());
    }
}
