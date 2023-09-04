package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TaskTest {

    @Test
    public void initialiseShouldBeFalseForIsMarked() {
        Task task = new Task("Test");
        Assertions.assertEquals(task.showMarked(), "[ ] ");
    }

    @Test
    public void setMarkedShouldBeTrueForIsMarked() {
        Task task = new Task("Test");
        task.setMarked();
        Assertions.assertEquals(task.showMarked(), "[X] ");
    }
}
