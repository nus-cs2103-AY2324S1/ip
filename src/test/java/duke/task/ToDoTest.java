package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void check_toString_output() {
        // Creation of new ToDo results in correct toString output
        assertEquals("[T] [ ] Homework", new ToDo("Homework").toString());

        // Creation of new ToDo results in correct format to be written to file
        assertEquals("T | 0 | Homework", new ToDo("Homework").toSaveFormat());
    }
}
