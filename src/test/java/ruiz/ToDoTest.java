package ruiz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ruiz.task.ToDo;

public class ToDoTest {
    @Test
<<<<<<< HEAD:src/test/java/ruiz/ToDoTest.java
    public void testMarkedSaveToString_success() {
        ToDo test = new ToDo("eat");
        assertEquals("T | 0 | eat", test.saveTaskString());
=======
    public void saveToString_success() {
        ToDo test = new ToDo("eat");
        assertEquals("T | 0 | eat", test.formatSaveTaskString());
>>>>>>> branch-A-CodeQuality:src/test/java/ruiz/ToDosTest.java
        test.mark();
        assertEquals("T | 1 | eat", test.formatSaveTaskString());
    }

    @Test
<<<<<<< HEAD:src/test/java/ruiz/ToDoTest.java
    public void testMarkTodo_success() {
=======
    public void toString_success() {
>>>>>>> branch-A-CodeQuality:src/test/java/ruiz/ToDosTest.java
        ToDo test = new ToDo("read book");
        assertEquals("[T][ ] read book", test.toString());
        test.mark();
        assertEquals("[T][X] read book", test.toString());
    }

}
