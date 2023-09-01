package TaskManager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ToDosTest {

    @Test
    void testToDos() throws IllegalArgumentException {

        ToDos newtodo = new ToDos("TestTodo");
        ToDos newtodo1 = new ToDos("TestTodo1");
        ToDos newtodo2 = new ToDos("TestTodo2");
        ToDos newtodo3 = new ToDos("TestTodo3");
        ToDos newtodo4 = new ToDos("TestTodo");
        ToDos newtodo5 = new ToDos("TestTodo5");
        ToDos newtodo6 = new ToDos("TestTodo");

        assertTrue(newtodo.equals(newtodo));
        assertFalse(newtodo.equals(newtodo1));
        assertFalse(newtodo.equals(newtodo2));
        assertFalse(newtodo.equals(newtodo3));
        assertTrue(newtodo.equals(newtodo4));
        assertFalse(newtodo.equals(newtodo5));
        assertTrue(newtodo.equals(newtodo6));

    }

}