package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void doneTest() {
        ToDo tsk = new ToDo("sleep");
        tsk.setCompleted();
        assertEquals("[X] ", tsk.getCheckbox());
    }

    @Test
    public void executeTest () {
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");
        TaskList lst = new TaskList();

        ToDo tsk = new ToDo("sleep");
        tsk.execute(lst, ui, storage);
        assertEquals(1, lst.size());
    }
}
