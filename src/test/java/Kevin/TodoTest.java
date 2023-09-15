package Kevin;

import Kevin.Task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[T][ ] return book",
                new Todo("return book", false).toString());
    }
}
