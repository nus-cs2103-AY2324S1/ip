package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
        private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Initialize the tasklist with no tasks
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddToDo() {
        taskList.createToDo("Do junit test");
        assertEquals(taskList.taskList.size(), 1);
    }

    @Test
    public void testUnMark() throws DukeException {
        taskList.createToDo("Do junit test");
        taskList.unmark(1);
        assertEquals(taskList.taskList.get(0).isDone, false);
    }

    @Test
    public void testUnMarkException() throws DukeException {
        taskList.createToDo("Do junit test");
        taskList.unmark(1);
        assertThrows(DukeException.class, () -> taskList.unmark(2));
    }

    @Test
    public void testMarkException() throws DukeException {
        taskList.createToDo("Do junit test");
        taskList.mark(1);
        assertThrows(DukeException.class, () -> taskList.mark(2));
    }

    @Test
    public void testMark() throws DukeException {
        taskList.createToDo("Do junit test");
        taskList.mark(1);
        assertEquals(taskList.taskList.get(0).isDone, true);
    }
}
