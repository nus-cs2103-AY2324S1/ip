package jarvis.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_correctOutput() {
        Task task = new Todo("Play Basketball", false);
        task.completeTask(true);
        assertEquals("[Todo] [X] Play Basketball", task.toString());
    }

    @Test
    public void toFile_correctOutput() {
        Task task = new Todo("Drive Car", false);
        assertEquals("T | O | Drive Car", task.toFile());
    }
}
