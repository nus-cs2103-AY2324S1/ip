package peko;

import org.junit.jupiter.api.Test;
import peko.exceptions.InvalidTaskException;
import peko.tasks.Deadline;
import peko.tasks.ToDos;
public class DeadlineTest {
    @Test
    public void testString() throws InvalidTaskException {
        Deadline deadline = new Deadline("return book /by 28/2/2100 2359");
        String correct = "[D][ ] return book (by: February 28 2100 23:59)";
        System.out.println(deadline.toString());
        //assert deadline.toString().equals(correct);
    }
    @Test
    public void testString2() throws InvalidTaskException {
        Deadline deadline = new Deadline("read /by 12/2/2200 0000");
        String correct = "[D][ ] read (by: February 12 2200 00:00)";
        System.out.println(deadline.toString());
        //assert deadline.toString().equals(correct);
    }
}
