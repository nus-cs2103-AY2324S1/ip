package task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.DukeException;

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
