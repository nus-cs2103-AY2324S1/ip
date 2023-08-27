package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void createNewTodo() {
        Todo task = new Todo("CS2103T ip");
        assertEquals(task.toString(), "[T][ ] CS2103T ip");
    }

    @Test
    public void markDoneTodo() {
        Todo task = new Todo("CS2103T ip");
        task.markAsDone();
        assertEquals(task.toString(), "[T][X] CS2103T ip");
    }

    @Test
    public void markUndoneTodo() {
        Todo task = new Todo("CS2103T ip");
        task.markAsDone();
        task.markAsUndone();
        assertEquals(task.toString(), "[T][ ] CS2103T ip");
    }
}
