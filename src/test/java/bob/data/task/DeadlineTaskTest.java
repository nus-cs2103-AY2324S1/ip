package bob.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class DeadlineTaskTest {
    @Test
    public void testStringConversion() {
        DeadlineTask testTask = new DeadlineTask("test", "01/01/2023 1200");
        assertEquals("[D][ ] test (by: 01 Jan 2023 12:00 PM)", testTask.toString());
        testTask.setDone();
        assertEquals("[D][X] test (by: 01 Jan 2023 12:00 PM)", testTask.toString());
    }

    @Test
    public void getType_emptyInput_correctString() {
        assertEquals("Deadline", new DeadlineTask("test", "01/01/2023 1200").getType());
    }

    @Test
    public void getDateTime_emptyInput_correctString() {
        assertEquals("01/01/2023 1200",
                new DeadlineTask("test", "01/01/2023 1200").getDateTime());
    }

    @Test
    public void toFileString_completedTask_stringWithCompleted() {
        DeadlineTask task = new DeadlineTask("test", "01/01/2023 1200");
        assertEquals("Deadline,0,test,01/01/2023 1200\n", task.toFileString());
    }

    @Test
    public void toFileString_incompleteTask_stringWithIncomplete() {
        DeadlineTask task = new DeadlineTask("test", "01/01/2023 1200");
        task.setDone();
        assertEquals("Deadline,1,test,01/01/2023 1200\n", task.toFileString());
    }
}
