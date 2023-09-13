package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.components.Status;

public class TaskTest {

    //this is the concrete implementation of Task, used for testing
    public class ConcreteTask extends Task {
        public ConcreteTask(Status status, String task) {
            super(status, task);
        }

        @Override
        public String convertTask() {
            return "task converted";
        }
    }

    @Test
    public void canMark_successful() {
        ConcreteTask task = new ConcreteTask(Status.NOT_DONE, "Set up unit tests");
        task.canMark();
        assertEquals(Status.DONE, task.getStatus());
    }

    @Test
    public void canMark_unsuccessful() {
        ConcreteTask task = new ConcreteTask(Status.DONE, "Set up unit tests");
        task.canMark();
        assertEquals(Status.DONE, task.getStatus());
    }

    @Test
    public void canUnMark_successful() {
        ConcreteTask task = new ConcreteTask(Status.DONE, "Set up unit tests");
        task.canUnMark();
        assertEquals(Status.NOT_DONE, task.getStatus());
    }

    @Test
    public void canUnMark_unsuccessful() {
        ConcreteTask task = new ConcreteTask(Status.NOT_DONE, "Set up unit tests");
        task.canUnMark();
        assertEquals(Status.NOT_DONE, task.getStatus());
    }

    @Test
    public void getStatus_uncompleted() {
        ConcreteTask task = new ConcreteTask(Status.NOT_DONE, "Set up unit tests");
        assertEquals(Status.NOT_DONE, task.getStatus());
    }

    @Test
    public void getStatus_completed() {
        ConcreteTask task = new ConcreteTask(Status.DONE, "Set up unit tests");
        assertEquals(Status.DONE, task.getStatus());
    }

    @Test
    public void getTask_completed() {
        ConcreteTask task = new ConcreteTask(Status.DONE, "Set up unit tests");
        assertEquals("Set up unit tests", task.getTask());
    }

    @Test
    public void toString_uncompleted() {
        ConcreteTask task = new ConcreteTask(Status.NOT_DONE, "Set up unit tests");
        assertEquals("[ ] " + "Set up unit tests", task.toString());
    }

    @Test
    public void toString_completed() {
        ConcreteTask task = new ConcreteTask(Status.DONE, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
}
