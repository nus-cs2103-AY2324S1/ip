package pau.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Carries out unit testing for Task List class.
 */
public class TaskListTest {

    /**
     * Task list to be tested.
     */
    private TaskList list;

    /**
     * Creates a task list before each test and
     * parses the printed output into a string.
     */
    @BeforeEach
    public void checkPrint() {
        list = new TaskList();
        list.addToDo("todo hang out with friends");
        list.addDeadline("deadline watch lecture /by 2023-09-23");
        list.addEvent("event soc bash /from friday 6pm /to 10pm");
    }

    /**
     * Tests if all tasks are printed correctly.
     */
    @Test
    public void checkList_tasks_printsAllTasks() {
        String expected = "sian you still have to complete these:\n\n"
                + "1. [T][ ] hang out with friends\n"
                + "2. [D][ ] watch lecture (by: Sep 23 2023)\n"
                + "3. [E][ ] soc bash (from: friday 6pm to: 10pm)\n";
        assertEquals(expected, list.checkList());
    }

    /**
     * Tests if a task is correctly deleted.
     */
    @Test
    public void deleteTask_taskExists_taskDeleted() {
        String expected = "not you running away from your responsibilities, "
                + "i guess you don't have to do this now:\n\n"
                + "[T][ ] hang out with friends\n\n"
                + "but still sucks to be you, you still have 2 tasks";
        assertEquals(expected, list.deleteTask("delete 1"));
    }

    /**
     * Tests if an error is printed when a non-existent task is deleted.
     */
    @Test
    public void deleteTask_taskDoesNotExist_errorMessagePrinted() {
        String expected = "there is no such task!!";
        assertEquals(expected, list.deleteTask("delete 5"));
    }
}
