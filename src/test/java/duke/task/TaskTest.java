package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
class ParserTest {


    private Task task = new Task("Test class");
    @Test
    public void testGetDescription_success() {
        assertEquals("Test class", task.getDescription());
    }

    @Test
    public void testToString_success() {

        assertEquals("[ ] Test class", task.toString());
    }
}
