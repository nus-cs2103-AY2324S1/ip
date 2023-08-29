package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void fromFileStringTest() {
        DeadlineTask deadlineTask = new DeadlineTask();
        try {
            deadlineTask.fromFileString("D | 0 | return book | 2/12/2019 1800");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals("[D][ ] return book (by: Dec 02 2019 1800)", deadlineTask.toString());
    }

    @Test
    public void toFileStringTest() {
        DeadlineTask deadlineTask = new DeadlineTask();
        try {
            deadlineTask = new DeadlineTask("return book", "2/12/2019 1800");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals("D |   | return book | 2/12/2019 1800", deadlineTask.toFileString());
    }
}
