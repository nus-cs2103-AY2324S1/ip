package alyssa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import alyssa.exceptions.AlyssaArgumentException;

/**
 * Here we test if the markTask function in TaskList is working as expected.
 * We ensure that it throws the correct exception when the deadline has missing
 * /by or incorrectly formatted /by, and that it works correctly with proper
 * deadlines.
 */
public class TaskListTest {

    /**
     * Verifies that adding a deadline with no /by field produces the correct Exception.
     */
    @Test
    public void addDeadline_invalidRest_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addDeadline("read book");
            fail();
        } catch (AlyssaArgumentException e) {
            assertEquals("Incorrect deadline syntax. Syntax: deadline desc /by date", e.getMessage());
        }
    }

    /**
     * Verifies that adding a deadline with a wrongly formatted /by field produces the correct Exception.
     */
    @Test
    public void addDeadline_invalidBy_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addDeadline("read book /by tmr");
            fail();
        } catch (AlyssaArgumentException e) {
            assertEquals("Invalid by. Syntax: yyyy-mm-dd", e.getMessage());
        }
    }

    /**
     * Verifies that adding a deadline with the correct syntax works as expected.
     */
    @Test
    public void addDeadline_validSyntax_success() {
        try {
            TaskList taskList = new TaskList();
            taskList.addDeadline("read book /by 2023-12-01");
            assertEquals(1, taskList.getTaskList().size());
        } catch (Exception e) {
            fail();
        }
    }
}
