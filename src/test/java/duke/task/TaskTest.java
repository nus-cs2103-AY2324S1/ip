package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;





public class TaskTest {

    @Test
    public void checkTaskEqualityTodo() {
        try {
            Task t1 = new Todo("todo crying");
            Task same = new Todo("todo crying");
            Task diff = new Todo("todo cryinggg");
            boolean a1 = t1.equals(same);
            boolean a2 = t1.equals(diff);
            assertTrue(a1);
            assertFalse(a2);
        } catch (DukeException e) {
            System.out.println("not suppose to happen");
        }
    }

    @Test
    public void checkMarkTask() {
        try {
            Task t1 = new Todo("todo crying");
            t1.markCompleted();
            assertEquals("[T][X] todo crying", t1.toString());
            t1.markUncompleted();
            assertEquals("[T][ ] todo crying", t1.toString());
        } catch (DukeException e) {
            System.out.println("not suppose to happen");
        }
    }


}
