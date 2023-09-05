package duke.tasks;

import duke.Ui;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void testMarkSuccess() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        task.mark();
        assertEquals(1, task.getStatus());
    }

    @Test
    public void testMarkNotSuccessful() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        task.mark();
        assertEquals(1, task.getStatus());
    }

    @Test
    public void testUnmarkSuccessful() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        task.unmark();
        assertEquals(0, task.getStatus());
    }

    @Test
    public void testUnmarkNotSuccessful() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        task.unmark();
        assertEquals(0, task.getStatus());
    }

    @Test
    public void testGetStatus1() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        assertEquals(0, task.getStatus());
    }

    @Test
    public void testGetStatus2() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals(1, task.getStatus());
    }

    @Test
    public void testTask1() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals("Set up unit tests", task.getTask());
    }

    @Test
    public void testToString1() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        assertEquals("[ ] " + "Set up unit tests", task.toString());
    }

    @Test
    public void testToString2() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
    @Test
    public void testToString3() {
        concreteTask task = new concreteTask(3, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
}
