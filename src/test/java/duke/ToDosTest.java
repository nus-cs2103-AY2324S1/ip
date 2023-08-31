package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDosTest {
    @Test
    public void toString_description_success() {
        ToDos toDoS = new ToDos("item 1");
        assertEquals("[T] [ ] item 1", toDoS.toString());
    }

    @Test
    public void toFileString_description_success() {
        ToDos toDos = new ToDos("item 1");
        assertEquals("T | F | item 1", toDos.toFileString());
    }

    @Test
    public void markDone_done_success() {
        ToDos toDos = new ToDos("item 1");
        toDos.markNotDone();
        assertFalse(toDos.isDone());
        toDos.markDone();
        assertTrue(toDos.isDone());
    }
}