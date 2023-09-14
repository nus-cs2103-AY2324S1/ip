package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    TaskList tasks = new TaskList();

    @Test
    public void testSetTodo() throws EmptyException {
        Todo.setTodo("todo play", tasks);
        assertEquals(tasks.get(0).toString(), "[T][ ] play");
    }
}
