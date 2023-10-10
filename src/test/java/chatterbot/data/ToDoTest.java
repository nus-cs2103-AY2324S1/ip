package chatterbot.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDo_description_addedToList() {
        // entered todo will be added successfully
        assertEquals("[T][ ] todo this assignment", new Todo("todo this assignment").toString());

        // entered todo will be added successfully
        assertEquals("[T][ ] todo chores", new Todo("todo chores").toString());
    }
}