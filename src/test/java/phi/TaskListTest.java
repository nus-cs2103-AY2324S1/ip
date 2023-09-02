package phi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markValid() {
        TaskList test = new TaskList();
        test.addTask(new ToDo("coding", false));
        String expected = "ALRIGHT NICE I'll mark this as completed :)\n[T][X] coding";
        assertEquals(expected, test.doTask("mark 1"));
    }

    @Test
    public void markInvalid() {
        TaskList test = new TaskList();
        test.addTask(new ToDo("coding", false));
        String expected = "OI open ur eyes and give a proper input ITS \"mark\" AND A NUMBER";
        assertEquals(expected, test.doTask("mark"));
    }

    @Test
    public void todoValid() {
        String expected = "Added:\n[T][ ] coding";
        assertEquals(expected, new TaskList().addTask(new ToDo("coding", false)));
    }

}
