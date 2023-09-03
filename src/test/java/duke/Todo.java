package duke;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Todo {

    @Test
    public void toString_success() throws Exception {
        assertEquals("[D][ ] nus boxing(by: 03 April 2003)", new Deadline("nus boxing", LocalDate.of(2003, 04, 03)).toString());
    }

    @Test
    public void changeFormat_success() {
        assertEquals(LocalDate.of(2003, 4, 3).toString(), "2003-04-03");
    }
}
