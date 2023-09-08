package chatterbot.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    public void toDo_description_addedToList() {
        // entered todo will be added successfully
        assertEquals("[T][ ] todo this assignment", new Todo("todo this assignment").toString());

        // entered todo will be added successfully
        assertEquals("[T][ ] todo chores", new Todo("todo chores").toString());
    }

    @Test
    public void toDo_empty_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Todo("");
        });
    }
}