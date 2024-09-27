package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {
    @Test
    void testToFileString() {
        ToDo test1 = new ToDo("test1");
        ToDo test2 = new ToDo("test2");
        test2.markAsDone();
        assertEquals("T | 0 | test1", test1.toFileString(), "toFileString an uncompleted task.");
        assertEquals("T | 1 | test2", test2.toFileString(), "toFileString a completed task.");
    }

    @Test
    void testToString() {
        ToDo test1 = new ToDo("test1");
        ToDo test2 = new ToDo("test2");
        test2.markAsDone();
        assertEquals("[T][ ] test1", test1.toString(), "String of an uncompleted task.");
        assertEquals("[T][X] test2", test2.toString(), "String of a completed task.");
    }
}
