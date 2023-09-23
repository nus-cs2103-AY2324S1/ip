package Kevin;

import Forgotten.Priority;
import Forgotten.Task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[T][ ] return book [P: HIGH]",
                new Todo("return book", false, Priority.HIGH).toString());
    }
}
