package puke;

import org.junit.jupiter.api.Test;
import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.task.Deadline;
import puke.task.Event;
import puke.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHandlerTest {
    @Test
    public void DataHandlerTests() throws PukeException {
        String firstInput = "[T]/0/borrow book/good";
        String secondInput = "[D]/0/return book/2019-12-01T10:00/bad";
        String secondInput2 = "return book/by 2019-12-01T10:00";
        String[] splitInput2 = secondInput2.split("/");
        String thirdInput = "[E]/0/project meeting/2023-08-30T14:00/2023-08-30T16:00/ugly";
        String thirdInput3 = "project meeting/from 2023-08-30T14:00/to 2023-08-30T16:00";
        String[] splitInput3 = thirdInput3.split("/");

        //Testing translateToDo
        String[] tags1 = new String[1];
        tags1[0] = "good";
        assertEquals(new ToDo("borrow book", tags1), DataHandler.translate(firstInput));

        //Testing translateDeadline
        String[] tags2 = new String[1];
        tags2[0] = "bad";
        assertEquals(new Deadline(splitInput2, tags2), DataHandler.translate(secondInput));

        //Testing translateEvent
        String[] tags3 = new String[1];
        tags3[0] = "ugly";
        assertEquals(new Event(splitInput3, tags3), DataHandler.translate(thirdInput));
    }
}
