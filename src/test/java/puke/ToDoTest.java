package puke;

import org.junit.jupiter.api.Test;
import puke.managers.PukeException;
import puke.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void ToDoTests() throws PukeException {
        String[] tags1 = new String[1];
        tags1[0] = "good";
        ToDo testObject = new ToDo("a", tags1);

        //Testing Write
        assertEquals("[T]/0/a/good", testObject.write());
        //Testing toString()
        assertEquals("[T][ ] a #good ", testObject.toString());
    }
}

