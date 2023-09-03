package duke;

import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testMark() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        TaskList tasks = new TaskList(storage);

        tasks.mark(0);
        assertEquals("X", tasks.taskList.get(0).getStatusIcon());
    }

    @Test
    public void testUnmark() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        TaskList tasks = new TaskList(storage);

        tasks.mark(0);
        tasks.unmark(0);
        assertEquals(" ", tasks.taskList.get(0).getStatusIcon());
    }

    @Test
    public void testAdd() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        ToDo todo = new ToDo("do physics");
        TaskList tasks = new TaskList(storage);

        tasks.add(todo);
        assertEquals("[T][ ] do physics", tasks.taskList.get(1).toString());
    }

    @Test
    public void testDelete() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        TaskList tasks = new TaskList(storage);

        tasks.delete(0);
        assertEquals(0, tasks.taskList.size());
    }

    @Test
    public void testMarkException() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        TaskList tasks = new TaskList(storage);

        try {
            tasks.mark(2);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There are only 1 tasks", e.getMessage());
        }
    }

    @Test
    public void testUnmarkException() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        TaskList tasks = new TaskList(storage);

        try {
            tasks.unmark(2);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There are only 1 tasks", e.getMessage());
        }
    }

    @Test
    public void testDeleteException() throws DukeException {
        ToDo sampleToDo = new ToDo("do econs");
        DataStorage storage = new DataStorage();
        storage.tasks.add(sampleToDo);
        TaskList tasks = new TaskList(storage);

        try {
            tasks.delete(2);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There are only 1 tasks", e.getMessage());
        }
    }

}
