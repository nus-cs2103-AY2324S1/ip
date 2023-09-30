package veneto.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void toString_toDoWithDescriptionOnly_unmarkedToDo() {
        assertEquals("[T][ ] a", new ToDo("a").toString());
    }

    @Test
    void toString_toDoWithDescriptionAndStatus_markedToDo() {
        assertEquals("[T][V] a", new ToDo("a", 1).toString());
    }

    @Test
    void saveToString_toDoWithDescriptionOnly_unmarkedToDo() {
        assertEquals("toDo,a,0", new ToDo("a").saveToString());
    }

    @Test
    void saveToString_toDoWithDescriptionAndStatus_markedToDo() {
        assertEquals("toDo,a,1", new ToDo("a", 1).saveToString());
    }
}