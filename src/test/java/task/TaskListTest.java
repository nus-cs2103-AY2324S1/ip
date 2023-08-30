package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void mark_withinBound_isDone() throws DukeException {
        TaskList list = new TaskList(new ArrayList<>());
        Task test = new ToDo("test");
        list.add(test);
        int idx = 1;
        list.mark(idx);

        assertTrue(test.isDone);
    }
}