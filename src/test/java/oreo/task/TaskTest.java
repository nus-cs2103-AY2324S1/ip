package oreo.task;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void generateTask_generateTodoTask_success() throws IllegalCommandException,
            IllegalDateTimeException {
        assertEquals(new ToDo("read book").toString(),
                Task.generateTask("todo", new Scanner("read book")).toString());
    }

    @Test
    public void generateTask_generateDeadlineTask_success() throws IllegalCommandException,
            IllegalDateTimeException {
        assertEquals(new Deadline("return book", "Sep 3 2023", null).toString(),
                Task.generateTask("deadline",
                        new Scanner("return book /by 03/09/2023")).toString());
    }

    @Test
    public void generateTask_generateEventTask_success() throws IllegalCommandException,
            IllegalDateTimeException {
        assertEquals(new Event("study at cafe", "Sep 3 2023", "3:00 PM",
                        "Sep 3 2023", "4:00 PM").toString(),
                Task.generateTask("event",
                        new Scanner("study at cafe /from 03/09/2023, 1500 /to 03/09/2023, 1600"))
                        .toString());
    }

    @Test
    public void generateTask_invalidCommand_exceptionThrown() throws IllegalDateTimeException {
        try {
            assertEquals(0, Task.generateTask("abc",
                    new Scanner("")));
            fail();
        } catch (IllegalCommandException e){
            assertEquals("I don't think I can process an empty task\n"
                    + "do you want to try again?", e.getMessage());
        }
    }

    @Test
    public void generateTask_invalidDate_exceptionThrown() throws IllegalCommandException {
        try {
            assertEquals(0, Task.generateTask("deadline",
                    new Scanner("return book /by 2023-09-03")));
            fail();
        } catch (IllegalDateTimeException e){
            assertEquals("Date or date format is invalid. Time or time format is invalid. "
                    + "If you are putting both date and time, you might have missed \", \" "
                    + "between the date and the time\n" +
                    " (e.g. \"1 Jan, 5pm\").\n" +
                    "Try \"help datetime\" to learn more about accepted date time formats", e.getMessage());
        }
    }

    @Test
    public void generateTask_invalidTime_exceptionThrown() throws IllegalCommandException {
        try {
            assertEquals(0, Task.generateTask("deadline",
                    new Scanner("return book /by 03/09/2023, 36pm")));
            fail();
        } catch (IllegalDateTimeException e){
            assertEquals("Time or time format is invalid. ", e.getMessage());
        }
    }

    @Test
    public void generateTask_invalidFromToTime_exceptionThrown() throws IllegalCommandException {
        try {
            assertEquals(0, Task.generateTask("event",
                    new Scanner("study /from 03/09/2023, 1600 /to 03/09/2023, 1400")));
            fail();
        } catch (IllegalDateTimeException e){
            assertEquals("Sorry but to time cannot be before from time\n" +
                    "2:00 PM is before 4:00 PM", e.getMessage());
        }
    }
}
