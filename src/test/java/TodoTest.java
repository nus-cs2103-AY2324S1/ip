import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {

    @Test
    public void toDoStringConversion() {
        String taskName = "return book";
        assertEquals("[T] [ ] return book", new Todo(taskName).toString());
    }
}
