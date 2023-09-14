package pogo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskDescriptionFilterTest {
    @Test
    void filter_emptyList_emptyList() {
        List<Task> tasks = new ArrayList<>();
        TaskDescriptionFilter tdf = new TaskDescriptionFilter("test");
        List<Task> filteredTasks = tdf.filter(tasks);
        assertTrue(filteredTasks.isEmpty());
    }

    @Test
    void filter_noMatch_emptyList() {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskDescriptionFilter tdf = new TaskDescriptionFilter("no match");
        List<Task> filteredTasks = tdf.filter(tasks);
        assertTrue(filteredTasks.isEmpty());
    }

    @Test
    void filter_matchOneTask_success() {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskDescriptionFilter tdf = new TaskDescriptionFilter("test");
        List<Task> filteredTasks = tdf.filter(tasks);
        assertFalse(filteredTasks.isEmpty());
        assertEquals(1, filteredTasks.size());
    }

    @Test
    void filter_matchManyTasks_success() {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("test"));
            tasks.add(new ToDo("test2"));
            tasks.add(new ToDo("test3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskDescriptionFilter tdf = new TaskDescriptionFilter("test");
        List<Task> filteredTasks = tdf.filter(tasks);
        assertFalse(filteredTasks.isEmpty());
        assertEquals(3, filteredTasks.size());
    }

    @Test
    void filter_partialMatchTasks_successAllMatch() {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("test"));
            tasks.add(new ToDo("test2"));
            tasks.add(new ToDo("test3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskDescriptionFilter tdf = new TaskDescriptionFilter("tt");
        List<Task> filteredTasks = tdf.filter(tasks);
        assertFalse(filteredTasks.isEmpty());
        assertEquals(3, filteredTasks.size());
    }

    @Test
    void filter_partialMatchTasks_successSomeMatch() {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new ToDo("test"));
            tasks.add(new ToDo("test2"));
            tasks.add(new ToDo("test3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskDescriptionFilter tdf = new TaskDescriptionFilter("tt");
        List<Task> filteredTasks = tdf.filter(tasks);
        assertFalse(filteredTasks.isEmpty());
        assertEquals(3, filteredTasks.size());
    }
}
