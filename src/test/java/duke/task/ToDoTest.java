package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDoTest() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T] [ ] read book", todo.toString());

        todo.markDone();
        assertEquals("[T] [X] read book", todo.toString());

        todo.markNotDone();
        assertEquals("[T] [ ] read book", todo.toString());
    }
}
