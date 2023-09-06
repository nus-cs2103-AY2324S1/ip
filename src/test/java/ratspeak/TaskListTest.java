package ratspeak;

import ratspeak.data.TaskList;
import ratspeak.exception.DukeException;
import ratspeak.task.Task;
import ratspeak.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void markTest() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new Todo("hello"));
            assertEquals("Nice! I've marked this task as done:\n[T][X] hello\n"
                    , new TaskList(tasks).mark("1"));
        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }


    }
}
