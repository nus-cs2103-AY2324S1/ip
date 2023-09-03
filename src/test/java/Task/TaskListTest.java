package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    // Test - private int computeDateTimeFormat(String input)

    // The method is used to identify dates in the below Local Date Time format :
    // "dd-MM-yyyy HHmm" with HHmm in the 24 hour format. Note: This method only checks for the
    // structure of the input. The validity of the Local Date Time Format is validated/tested later.
    // The method will return 1 if it is in LocalDateTimeFormat, 0 if it is a custom string format


    @Test
    public void localDateTimeShouldReturnOne() {
        TaskList task = new TaskList();
        String input = "16-08-1977 1700";

        assertEquals(1, task.computeDateTimeFormat(input));
    }

    @Test
    public void customInputShouldReturnZero() {
        TaskList task = new TaskList();
        String input = "16 August 1977 5pm";

        assertEquals(0, task.computeDateTimeFormat(input));
    }

}
