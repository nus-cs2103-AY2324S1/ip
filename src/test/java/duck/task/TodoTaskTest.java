package duck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTaskTest {
    @Test
    public void stringify_success() {
        TodoTask task = new TodoTask("do laundry", false);
        assertEquals("T010/do laundry", task.stringify());

        task = new TodoTask("finish homework", true);
        assertEquals("T115/finish homework", task.stringify());
    }

    @Test
    public void parse_success() {
        try {
            TodoTask task = new TodoTask("do laundry", false);
            String taskString = task.stringify();
            Task parsedTask = TodoTask.parse(taskString);
            assertEquals(task, parsedTask);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toString_success() {
        TodoTask task = new TodoTask("do laundry", false);
        assertEquals("[T][ ] do laundry", task.toString());

        task = new TodoTask("finish homework", true);
        assertEquals("[T][X] finish homework", task.toString());
    }
}
