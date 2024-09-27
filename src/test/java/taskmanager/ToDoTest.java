package taskmanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testToDos() throws IllegalArgumentException {

        ToDo newtodo = new ToDo("TestTodo");
        ToDo newtodo1 = new ToDo("TestTodo1");
        ToDo newtodo2 = new ToDo("TestTodo2");
        ToDo newtodo3 = new ToDo("TestTodo3");
        ToDo newtodo4 = new ToDo("TestTodo");
        ToDo newtodo5 = new ToDo("TestTodo5");
        ToDo newtodo6 = new ToDo("TestTodo");

        assertTrue(newtodo.equals(newtodo));
        assertFalse(newtodo.equals(newtodo1));
        assertFalse(newtodo.equals(newtodo2));
        assertFalse(newtodo.equals(newtodo3));
        assertTrue(newtodo.equals(newtodo4));
        assertFalse(newtodo.equals(newtodo5));
        assertTrue(newtodo.equals(newtodo6));
    }
}
