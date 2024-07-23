package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class TodoTest {
    @Test
    void testGenerateStr() {
        Todo task1 = new Todo("task1", false);
        Todo task2 = new Todo("task2", false);
        task2.mark();
        assertEquals("T | 0 | task1",
                task1.generateStr(), "Generate str of uncompleted task");
        assertEquals("T | 1 | task2", task2.generateStr(), "Generate str of completed task");
    }

    @Test
    void testToString() {
        Todo task1 = new Todo("task1", false);
        Todo task2 = new Todo("task2", false);
        task2.mark();
        assertEquals("[T][ ] task1",
                task1.toString(), "To String of uncompleted task");
        assertEquals("[T][X] task2", task2.toString(), "To String of completed task");
    }

}
