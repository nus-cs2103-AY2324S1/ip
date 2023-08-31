package main;

import command.CommandException;
import org.junit.jupiter.api.Test;
import task.EventTask;
import task.Task;
import task.TaskList;
import util.DateTimeUtil;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    /**
     * Test cases for DateTimeUtil.parseDateTimeString
     */
    @Test
    public void dateTimeParseTest() {
        try{
            assertEquals(DateTimeUtil.parseDateTimeString("1970-01-01").toString(), "1970-01-01T00:00");
            assertEquals(DateTimeUtil.parseDateTimeString("1970-01-01 05:01:03").toString(), "1970-01-01T05:01:03");
        }
        catch (Exception e){
            fail();
        }
        assertThrows(CommandException.class, () -> DateTimeUtil.parseDateTimeString("1970 01 01"));
        assertThrows(CommandException.class, () -> DateTimeUtil.parseDateTimeString("1970-01-01 00:00:100"));
    }

    /**
     * Test cases for testing some exceptions in the program
     */
    @Test
    public void exceptionTest(){
        // Event tasks: start time must <= end time
        assertThrows(CommandException.class, ()->new EventTask("something", "2023-09-01 00:00:05", "2023-09-01 00:00:03"));
        assertDoesNotThrow(()->new EventTask("something", "2023-09-01 00:00:03", "2023-09-01 00:00:05"));
        TaskList testTaskList = new TaskList();
        // No duplicate named tasks
        assertDoesNotThrow(()->testTaskList.addTask(new Task("name")));
        assertThrows(CommandException.class, ()->testTaskList.addTaskAndSay(new Task("name")));
    }
}
