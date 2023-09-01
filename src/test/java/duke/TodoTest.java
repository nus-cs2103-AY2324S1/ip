package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] Apply for internships", new ToDo("Apply for internships").toString());

        Task task = new ToDo("Make my resume");
        task.mark();

        assertEquals("[T][X] Make my resume", task.toString());
    }

    @Test
    public void testWriteToFile() {
        assertEquals("T | 0 | Apply for internships", new ToDo("Apply for internships").writeToFile());

        Task task = new ToDo("Make my resume");
        task.mark();

        assertEquals("T | 1 | Make my resume", task.writeToFile());
    }
}
