package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markAsCompleteTest(){
        Todo todo = new Todo("Math homework");
        todo.markAsComplete();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void markAsIncompleteTest(){
        Todo todo = new Todo("Science homework");
        todo.markAsComplete();
        todo.markAsIncomplete();
        assertEquals(" ", todo.getStatusIcon());
    }
}
