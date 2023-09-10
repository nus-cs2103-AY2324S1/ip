package koko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;
    private Task sampleTask1;
    private Task sampleTask2;

    @BeforeEach
    public void setup() {
        sampleTask1 = new Todo("sample task 1");
        sampleTask2 = new Todo("sample task 2");
        ArrayList<Task> sampleTasks = new ArrayList<>();
        sampleTasks.add(sampleTask1);
        sampleTasks.add(sampleTask2);

        taskList = new TaskList(sampleTasks);
    }

    @Test
    public void testDeleteTaskAtIndex_validIndex() {
        try {
            Task deletedTask = taskList.deleteTaskAtIndex(0);
            assertEquals(sampleTask1, deletedTask);
            assertEquals(1, taskList.size());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteTaskAtIndex_invalidIndex() {
        assertThrows(DukeException.class, () -> taskList.deleteTaskAtIndex(-1));
        assertThrows(DukeException.class, () -> taskList.deleteTaskAtIndex(3));
    }

    @Test
    public void testMarkTaskAtIndex_validIndex() {
        try {
            Task markedTask = taskList.markTaskAtIndex(0);
            assertEquals(sampleTask1, markedTask);
            assertEquals("[X]", markedTask.toString().substring(3, 6));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMarkTaskAtIndex_invalidIndex() {
        assertThrows(DukeException.class, () -> taskList.markTaskAtIndex(-1));
        assertThrows(DukeException.class, () -> taskList.markTaskAtIndex(3));
    }
}
