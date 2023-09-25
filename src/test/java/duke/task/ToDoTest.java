package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDoTaskType_shouldBeT() {
        Task task = new ToDo("test");
        String expected = "T";
        String actual = task.getTaskType();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isMarkedForInitialisedToDo_shouldBeFalse() {
        Task task = new ToDo("test");
        Assertions.assertEquals("[ ] ", task.showMarked());
    }

    @Test
    public void isMarkedForSetMarked_shouldBeTrue() {
        Task task = new ToDo("test");
        task.setMarked();
        Assertions.assertEquals("[X] ", task.showMarked());
    }
}
