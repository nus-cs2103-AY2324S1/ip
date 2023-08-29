package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {
    @Test
    public void taskStatus() {
        assertEquals(" ", new Todo("eat").getStatusIcon());
    }

    @Test
    public void taskRepresentation() throws DukeException {
        assertEquals( "[D][ ] return books (by: Aug 8 2023)", new Deadline("return books", "2023-08-08").toString());
    }
}
