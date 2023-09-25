package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TaskListTest {
    private DtFormat dtf = new DtFormat();

    @Test
    public void testTaskListSize() throws DukeException {
        TaskList tl = new TaskList();

        Deadline deadline = new Deadline("help", true, "/by 8/8/2020 1630");
        tl.addItem(deadline);
        tl.addItem(deadline);
        tl.addItem(deadline);
        assertEquals(tl.getSize(), 3);
    }

    @Test
    public void testGetItem() throws DukeException {
        TaskList tl = new TaskList();
        Deadline deadline = new Deadline("help", true, "/by 8/8/2020 1630");
        tl.addItem(null);
        tl.addItem(deadline);
        tl.addItem(null);
        assertEquals(tl.getItem(1), deadline);
        assertEquals(deadline.equals(tl.getItem(0)), false);
    }


}
