package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][X] hello", new Todo("hello", true).toString());

    }
}
