package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;


public class TaskListTest {

    @Test
    public void check_writeFormat() {
        //Checks if the write format gives the correct string output
        Ui ui = new Ui();
        TaskList tasklst = new TaskList();
        tasklst.addTask(new ToDo("Homework"), ui);
        assertEquals("T | 0 | Homework", tasklst.toWriteFormat().get(0));
    }
}
