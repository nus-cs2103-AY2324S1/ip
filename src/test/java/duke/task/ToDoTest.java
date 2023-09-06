package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
public class ToDoTest {
    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[T][ ] Test TODO",
                new ToDo("Test TODO").toString());
    }


}
