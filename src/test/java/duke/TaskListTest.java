package duke;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testTaskListInitialisation() {
        ArrayList<String[]> list = new ArrayList<>();
        list.add(new String[]{"T", "0", "CS2103 iP"});
        list.add(new String[]{"E", "0", "Mom's birthday", "2023-08-17 0000", "2023-08-17 2359"});
        list.add(new String[]{"D", "1", "ST2334 Tutorial", "2023-09-01 1400"});

        TaskList taskList = new TaskList(list);
        assertEquals(taskList.getLength(), 3);
        assertEquals(taskList.getTask(0).toString(), "[T][ ] CS2103 iP");
        assertEquals(taskList.getTask(2).toString(), "[D][X] ST2334 Tutorial (by: Sep 1 2023 1400)");
    }

    @Test
    public void testDeleteTask() throws DukeException {
        ArrayList<String[]> list = new ArrayList<>();
        list.add(new String[]{"T", "0", "CS2103 iP"});
        list.add(new String[]{"E", "0", "Mom's birthday", "2023-08-17 0000", "2023-08-17 2359"});
        list.add(new String[]{"D", "1", "ST2334 Tutorial", "2023-09-01 1400"});

        TaskList taskList = new TaskList(list);
        assertEquals(taskList.getTask(1).toString(),
                "[E][ ] Mom's birthday (from: Aug 17 2023 0000 to: Aug 17 2023 2359)");
        taskList.delete(1);
        assertEquals(taskList.getLength(), 2);
        assertEquals(taskList.getTask(1).toString(), "[D][X] ST2334 Tutorial (by: Sep 1 2023 1400)");
    }
}
