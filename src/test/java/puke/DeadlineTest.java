package puke;

import org.junit.jupiter.api.Test;
import puke.managers.PukeException;
import puke.task.Deadline;
import puke.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineTests() throws PukeException {
        String[] tags1 = new String[1];
        tags1[0] = "bad";
        String objectInput = "return book /by 2019-12-01T10:00";
        Deadline testObject = new Deadline(objectInput.split("/"), tags1);

        //Testing Write
        assertEquals("[D]/0/return book /2019-12-01T10:00/bad", testObject.write());
        //Testing toString()
        assertEquals("[D][ ] return book  (by: 2019-12-01T10:00) #bad ", testObject.toString());
    }
}
