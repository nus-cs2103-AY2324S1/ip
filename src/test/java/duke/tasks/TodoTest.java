package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        Task task = new Todo("Play Basketball");
        task.completeTask();
        assertEquals("[Todo] [X] Play Basketball", task.toString());
    }

    @Test
    public void toFileTest(){
        Task task = new Todo("Drive Car");
        assertEquals("T | O | Drive Car", task.toFile());
    }
}
