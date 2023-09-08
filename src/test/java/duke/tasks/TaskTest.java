package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    //this is the concrete implementation of Task, used for testing
    public class ConcreteTask extends Task {
        public ConcreteTask(int status, String task) {
            super(status, task);
        }

        @Override
        public String convertTask() {
            return "task converted";
        }
    }

    @Test
    public void canMark_successful() {
        ConcreteTask task = new ConcreteTask(0, "Set up unit tests");
        task.canMark();
        assertEquals(1, task.getStatus());
    }

    @Test
    public void canMark_unsuccessful() {
        ConcreteTask task = new ConcreteTask(1, "Set up unit tests");
        task.canMark();
        assertEquals(1, task.getStatus());
    }

    @Test
    public void canUnMark_successful() {
        ConcreteTask task = new ConcreteTask(1, "Set up unit tests");
        task.canUnMark();
        assertEquals(0, task.getStatus());
    }

    @Test
    public void canUnMark_unsuccessful() {
        ConcreteTask task = new ConcreteTask(0, "Set up unit tests");
        task.canUnMark();
        assertEquals(0, task.getStatus());
    }

    @Test
    public void getStatus_uncompleted() {
        ConcreteTask task = new ConcreteTask(0, "Set up unit tests");
        assertEquals(0, task.getStatus());
    }

    @Test
    public void getStatus_completed() {
        ConcreteTask task = new ConcreteTask(1, "Set up unit tests");
        assertEquals(1, task.getStatus());
    }

    @Test
    public void getTask_completed() {
        ConcreteTask task = new ConcreteTask(1, "Set up unit tests");
        assertEquals("Set up unit tests", task.getTask());
    }

    @Test
    public void toString_uncompleted() {
        ConcreteTask task = new ConcreteTask(0, "Set up unit tests");
        assertEquals("[ ] " + "Set up unit tests", task.toString());
    }

    @Test
    public void toString_completed() {
        ConcreteTask task = new ConcreteTask(1, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
}
