package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;



public class TaskListTest {
    @Test
    public void checkDuplicatesInTasklist() {
        try {
            Task t = new Todo("todo crying");
            Task t1 = new Todo("todo crying");
            ArrayList<Task> al = new ArrayList<>();
            al.add(t);
            TaskList tl = new TaskList(al);
            tl.addTask(t1);
            boolean check = tl.verifyNoDuplicates(t1);
            assertEquals(false, check);
        } catch (DukeException e) {
            System.out.println("Not suppose to happen");
        }

    }
}
