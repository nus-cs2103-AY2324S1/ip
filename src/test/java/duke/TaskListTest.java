package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList tasks = new TaskList();

    @Test
    public void testMark() {
        tasks.add(new Todo("play"));
        assertEquals(tasks.get(0).getStatusIcon(), " ");
        tasks.get(0).mark();
        assertEquals(tasks.get(0).getStatusIcon(), "X");
    }

    @Test
    public void testDelete() {
        tasks.add(new Todo("eat"));
        assertEquals(tasks.size(), 1);
        tasks.delete(new String[]{"delete", "1"});
        assertEquals(tasks.size(), 0);
    }
}
