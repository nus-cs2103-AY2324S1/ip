package juke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void deleteTask_indexOutOfBounds_errorThrown() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            tasks.delete(1);
        } catch (JukeError e) {
            assertEquals("That task does not exist!", e.getMessage());
        }
    }
    @Test
    public void markTask_indexOutOfBounds_errorThrown() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            tasks.markAsDone(1);
        } catch (JukeError e) {
            assertEquals("That task does not exist!", e.getMessage());
        }
    }
    @Test
    public void add_getSize_success() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new Task("testing"));
        tasks.add(new Task("testing"));
        assertEquals(tasks.getSize(), 2);
    }
}
