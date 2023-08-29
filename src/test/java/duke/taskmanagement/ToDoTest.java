package duke.taskmanagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todoTest1() {
        Task todo = new ToDo("eat", false);
        assertEquals(todo.toString(),"[T][ ] eat");
    }

    @Test
    public void todoTest2() {
        ToDo todo = new ToDo("test", false);
        assertEquals(todo.convertIsDone(), "0");
    }
}
