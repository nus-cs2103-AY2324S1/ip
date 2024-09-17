package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todoMarkAsDone_success() {
        Todo todoTask = new Todo("Read Book");
        todoTask.markAsDone();
        assertEquals("[T][X] Read Book", todoTask.toString());
    }

    @Test
    public void todoUnmark_success() {
        Todo todoTask = new Todo("Read Book");
        todoTask.markAsDone();
        todoTask.unmark();
        assertEquals("[T][ ] Read Book", todoTask.toString());
    }

    @Test
    public void todoComplicatedName_success() {
        try {
            Todo todoTask = new Todo("1234567890-=+qwertyuiop[]';.,/asdfghkl'");
            assertEquals("[T][ ] 1234567890-=+qwertyuiop[]';.,/asdfghkl'", todoTask.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
