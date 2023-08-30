package data.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.beans.Transient;

import exception.InvalidInputException;


public class DeadlineTest {
    public void toStringTest() throws InvalidInputException {
        Deadline deadline = new Deadline();
        deadline.setDescription("return book");
        
        deadline.setBy("2020-08-23");
        assertEquals("[D][ ] return book (by: Aug 23 2020)", deadline.toString());
    }


    public void setByTest() throws InvalidInputException {
        Deadline deadline = new Deadline();
        deadline.setDescription("return book");
        deadline.setBy("2020-08-23");
        deadline.setBy("2020-08-23 23:59");
    }

    
}
