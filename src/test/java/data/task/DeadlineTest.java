package data.task;


import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.data.task.Deadline;
import org.junit.jupiter.api.Test;

import duke.exception.InvalidInputException;


public class DeadlineTest {
    @Test
    public void toStringTest() throws InvalidInputException {
        Deadline deadline = new Deadline();
        deadline.setDescription("return book");
        
        deadline.setBy("2020-08-23");
        assertEquals("[D][ ] return book (by: Aug 23 2020)", deadline.toString());
    }

    @Test
    public void setByTest() throws InvalidInputException {
        Deadline deadline = new Deadline();
        deadline.setDescription("return book");
        deadline.setBy("2020-08-23");
        try {
            deadline.setBy("2020-08-23 12:00");
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input: Invalid date format, should be YYYY-MM-DD", e.getMessage());
        }
       
    }

    
}
