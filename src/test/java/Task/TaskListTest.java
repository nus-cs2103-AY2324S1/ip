package Task;

import java.util.ArrayList;
import Exception.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void mark_withinBound_success() throws DukeException {
        TaskList list = new TaskList(new ArrayList<>());
        Task test = new ToDo("test");
        list.add(test);
        int idx = 1;
        list.mark(idx);

        assertTrue(test.isDone);
    }
}