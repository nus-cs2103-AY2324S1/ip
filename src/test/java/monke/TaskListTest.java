package monke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import monke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList tasks;
    private Todo taskOne;
    private Todo taskTwo;
    private Todo taskThree;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
        try {
            this.taskOne = new Todo("task 1");
            this.taskTwo = new Todo("task 2");
            this.taskThree = new Todo("task 3");
            tasks.add(taskOne);
            tasks.add(taskTwo);
            tasks.add(taskThree);
        } catch (MonkeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getTask_numbersWithinRange_success() throws MonkeException {
        assertEquals(taskOne, tasks.getTask("1"));
        assertEquals(taskTwo, tasks.getTask("2"));
        assertEquals(taskThree, tasks.getTask("3"));
    }

    @Test
    public void getTask_zeroInput_exceptionThrown() {
        try {
            assertEquals(taskOne, tasks.getTask("0"));
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Your number is out of range. :(", e.getMessage());
        }
    }

    @Test
    public void getTask_numberMoreThanListLength_exceptionThrown() {
        try {
            tasks.getTask("4");
            assertEquals(taskOne, tasks.getTask("5"));
            assertEquals(taskOne, tasks.getTask("6"));
            assertEquals(taskOne, tasks.getTask("1000"));
            fail();
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Your number is out of range. :(", e.getMessage());
        }
    }

    @Test
    public void getTask_negativeNumber_exceptionThrown() {
        try {
            assertEquals(taskOne, tasks.getTask("-1"));
        } catch (MonkeException e) {
            assertEquals("OOGA BOOGA!! Your number is out of range. :(", e.getMessage());
        }
    }
}