package seedu;  //same package as the class being tested

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void statusMarkTest(){
        Task newTask = new Task("todo cs2103t", "todo");
        newTask.mark();
        assertEquals("[T][X] todo cs2103t", newTask.getStatus());
    }

    @Test
    public void markTest(){
        try {
            Task newTask = new Task("todsadao cs2103t", "toddsado");
        } catch(Exception e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}