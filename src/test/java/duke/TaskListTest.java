package duke;

import duke.exceptions.IncompleteDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTask_success() {
        TaskList lst = new TaskList();
        try {
            ToDo todo1 = new ToDo("1");
            ToDo todo2 = new ToDo("2");
            ToDo todo3 = new ToDo("3");
            lst.addTask(todo1);
            lst.addTask(todo2);
            lst.addTask(todo3);
            assertEquals(lst.getTask(0).toString(), todo1.toString());
            assertEquals(lst.getTask(1).toString(), todo2.toString());
            assertEquals(lst.getTask(2).toString(), todo3.toString());
        } catch (IncompleteDescriptionException | InvalidTaskIndexException ex) {
            fail();
        }
    }

    @Test
    public void getTask_indexOutOfBounds_fail() {
        TaskList lst = new TaskList();
        try {
            lst.getTask(1);
            fail();
        } catch (InvalidTaskIndexException ex) {
            String output = Ui.divider + "\n"
                    + "☹ OOPS!!! You keyed in an invalid task index!"
                    + "\n" + Ui.divider + "\n";
            assertEquals(output, ex.toString());
        }
    }
}
