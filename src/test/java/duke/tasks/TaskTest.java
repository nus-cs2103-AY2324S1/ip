package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    //this is the concrete implementation of Task, used for testing
    public class concreteTask extends Task {
        public concreteTask(int status, String task) {
            super(status, task);
        }

        @Override
        public String convertTask() {
            return "task converted";
        }
    }

    @Test
    public void canMark_successful() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        task.canMark();
        assertEquals(1, task.getStatus());
    }

    @Test
    public void canMark_unsuccessful() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        task.canMark();
        assertEquals(1, task.getStatus());
    }

    @Test
    public void canUnMark_successful() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        task.canUnMark();
        assertEquals(0, task.getStatus());
    }

    @Test
    public void canUnMark_unsuccessful() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        task.canUnMark();
        assertEquals(0, task.getStatus());
    }

    @Test
    public void getStatus_uncompleted() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        assertEquals(0, task.getStatus());
    }

    @Test
    public void getStatus_completed() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals(1, task.getStatus());
    }

    @Test
    public void getTask_completed() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals("Set up unit tests", task.getTask());
    }

    @Test
    public void toString_uncompleted() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        assertEquals("[ ] " + "Set up unit tests", task.toString());
    }

    @Test
    public void toString_completed() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
}
