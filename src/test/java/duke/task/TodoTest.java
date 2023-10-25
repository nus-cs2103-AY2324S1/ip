package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.util.Keyword;
import duke.util.Storage;

/**
 * JUnit test class for Todo.
 */
public class TodoTest {

    @Test
    public void toStringTest() {
        Todo t = new Todo("return book");
        assertEquals("[T][ ] return book", t.toString());
        t.mark(true);
        assertEquals("[T][X] return book", t.toString());
        t.mark(false);
        assertEquals("[T][ ] return book", t.toString());
    }

    @Test
    public void fileFormatTest() {
        Todo t = new Todo("return book");
        String expectedUnmark = "T" + Storage.SEPARATOR + "0" + Storage.SEPARATOR + "return book\n";
        String expectedMark = "T" + Storage.SEPARATOR + "1" + Storage.SEPARATOR + "return book\n";
        assertEquals(expectedUnmark, t.fileFormat());
        t.mark(true);
        assertEquals(expectedMark, t.fileFormat());
        t.mark(false);
        assertEquals(expectedUnmark, t.fileFormat());
    }

    @Test
    public void onDateTest() {
        Todo d = new Todo("return book");
        assertFalse(d.onDate(Keyword.EVENT, LocalDate.parse("2023-12-02")));
        assertFalse(d.onDate(Keyword.DEADLINE, LocalDate.parse("2023-12-02")));
        assertFalse(d.onDate(Keyword.TODO, LocalDate.parse("2023-12-01")));
    }
}
