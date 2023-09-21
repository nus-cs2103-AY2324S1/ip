package phi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import phi.task.ToDo;
import phi.util.PhiException;
import phi.util.TaskList;

public class TaskListTest {
    @Test
    public void markValid() {
        TaskList test = new TaskList();
        test.addTask(new ToDo("coding", false));
        String expected = "ALRIGHT NICE I'll mark this as completed :)\n[T][X] coding";

        String result;
        try {
            result = test.doTask("mark 1");
        } catch (PhiException p) {
            result = p.getMessage();
        }
        assertEquals(expected, result);
    }

    @Test
    public void markInvalid() {
        TaskList test = new TaskList();
        test.addTask(new ToDo("coding", false));
        String expected = "OI open ur eyes and give a proper input ITS \"mark\" AND A NUMBER";

        String result;
        try {
            result = test.doTask("mark");
        } catch (PhiException p) {
            result = p.getMessage();
        }
        assertEquals(expected, result);

        assertEquals(expected, result);
    }

    @Test
    public void todoValid() {
        String expected = "Added:\n[T][ ] coding";
        assertEquals(expected, new TaskList().addTask(new ToDo("coding", false)));
    }

}
