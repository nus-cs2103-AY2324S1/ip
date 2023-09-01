package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void check_todo_contentLine() {

        Todo t = new Todo("Checking", false);
        assertEquals("T/ /Checking", t.contentLine());
    }

    @Test
    public void check_todo_toString() {

        Todo t = new Todo("Checking", false);
        assertEquals("[T][ ] Checking", t.toString());
    }
}
