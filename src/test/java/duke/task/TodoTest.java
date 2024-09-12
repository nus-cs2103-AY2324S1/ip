package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void toStringTest() {
        Todo todo = new Todo("Sample Todo");
        assertEquals("[T][ ] Sample Todo", todo.toString());

        todo.markAsDone();
        assertEquals("[T][X] Sample Todo", todo.toString());
    }
}
