package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest extends TaskTest {

    @Test
    public void toString_withCorrectFormat_success() {
        Task todo = new Todo("Sleeping");
        assertEquals("[T][ ] Sleeping", todo.toString());
    }
}
