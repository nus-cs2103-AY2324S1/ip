package duke;

import duke.mocks.TaskMock;
import duke.mocks.TaskStorageMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void constructor_normal_success() {
        assertDoesNotThrow(() -> new TaskList(new TaskStorageMock(null)));
    }

    @Test
    public void constructor_someTasks_success() {
        TaskStorage taskStorage = new TaskStorageMock(
                new ArrayList<>(List.of(
                        new TaskMock("A"),
                        new TaskMock("B"),
                        new TaskMock("C")
                ))
        );
        assertDoesNotThrow(() -> new TaskList(taskStorage));
    }

    @Test
    public void constructor_getTasks_success() throws Exception {
        TaskStorage taskStorage = new TaskStorageMock(
                new ArrayList<>(List.of(
                        new TaskMock("A"),
                        new TaskMock("B"),
                        new TaskMock("C")
                ))
        );
        TaskList taskList = new TaskList(taskStorage);
        List<Task> tasks = taskList.getTasks();
        assertEquals(tasks.size(), 3);
        assertEquals(tasks.get(0).toString(), "A");
        assertEquals(tasks.get(1).toString(), "B");
        assertEquals(tasks.get(2).toString(), "C");
    }

    @Test
    public void addTask_normal_success() throws Exception {
        TaskStorage taskStorage = new TaskStorageMock(new ArrayList<>(List.of()));
        TaskList taskList = new TaskList(taskStorage);

        taskList.addTask(new TaskMock("A"));
        List<Task> tasks = taskList.getTasks();
        assertEquals(tasks.size(), 1);
        assertEquals(tasks.get(0).toString(), "A");

        taskList.addTask(new TaskMock("B"));
        tasks = taskList.getTasks();
        assertEquals(tasks.size(), 2);
        assertEquals(tasks.get(0).toString(), "A");
        assertEquals(tasks.get(1).toString(), "B");
    }

    @Test
    public void deleteTask_normal_success() throws Exception {
        TaskStorage taskStorage = new TaskStorageMock(
                new ArrayList<>(List.of(
                        new TaskMock("A"),
                        new TaskMock("B"),
                        new TaskMock("C")
                ))
        );
        TaskList taskList = new TaskList(taskStorage);

        taskList.deleteTask(2);
        List<Task> tasks = taskList.getTasks();
        assertEquals(tasks.size(), 2);
        assertEquals(tasks.get(0).toString(), "A");
        assertEquals(tasks.get(1).toString(), "C");
    }

    @Test
    public void deleteTask_outOfBounds_success() throws Exception {
        TaskStorage taskStorage = new TaskStorageMock(
                new ArrayList<>(List.of(
                        new TaskMock("A"),
                        new TaskMock("B"),
                        new TaskMock("C")
                ))
        );
        TaskList taskList = new TaskList(taskStorage);
        assertThrows(DukeException.class, () -> taskList.deleteTask(9));
    }
}
