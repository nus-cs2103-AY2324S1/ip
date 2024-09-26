package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddTaskTest {
    @Test
    public void executeTest() {
        Ui ui = new Ui();
        Storage storage = new Storage(Storage.getRelativePath() + "\\TaskList.txt");
        TaskList lst = new TaskList();

        ToDo tsk = new ToDo("sleep");
        AddTask command = new AddTask(tsk);
        command.execute(lst, ui, storage);
        assertEquals(1, lst.size());
    }
}
