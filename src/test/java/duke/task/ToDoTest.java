package duke.task;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToString() {
        ToDo testTask = new ToDo("Test ToDo");
        assert testTask.toString().equals("[T][ ] Test ToDo");
    }

    @Test
    public void testMarkAsDone() {
        ToDo task1 = new ToDo("Test ToDo");
        task1.markAsDone();
        assert task1.toString().equals("[T][X] Test ToDo");
    }

}
