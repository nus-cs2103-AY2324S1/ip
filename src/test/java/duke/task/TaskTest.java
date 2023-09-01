package duke.task;

import duke.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
class ParserTest {


    Task task = new Task("Test class");
    @Test
    public void testGetDescription_success() {
        assertEquals("Test class", task.getDescription());
    }

    @Test
    public void testToString_success() {

        assertEquals("[ ] Test class", task.toString());
    }
}
