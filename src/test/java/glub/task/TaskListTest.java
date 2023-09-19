package glub.task;  //same package as the class being tested

import glub.Glub;
import glub.GlubException;
import glub.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTask_emptyTask_exceptionThrown(){
        try {
            new TaskList(new Storage("dummystorage.txt")).addTask("", TaskType.TODO, false);
            fail(); // the test should not reach this line
        } catch (GlubException e) {
            assertEquals("OOPS!! The description of a TODO cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void addTask_noDeadline_exceptionThrown(){
        try {
            new TaskList(new Storage("dummystorage.txt"))
                    .addTask("run", TaskType.DEADLINE, false);
            fail(); // the test should not reach this line
    } catch (GlubException e) {
        assertEquals("OOPS!! Please provide a deadline for your deadline task.\n", e.getMessage());
    }
    }
}