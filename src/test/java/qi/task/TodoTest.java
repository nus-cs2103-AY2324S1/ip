package qi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringDescription() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }
}
