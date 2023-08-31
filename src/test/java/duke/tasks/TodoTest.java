package duke.tasks;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {

    @Test
    public void toString_default() {
        Todo testTodo = new Todo("Test Todo");
        assertEquals(testTodo.toString(), "[T][ ] Test Todo");
    }

    @Test
    public void toString_checkAndUncheck() {
        Todo testTodo = new Todo("Test Todo");
        testTodo.markDone();
        assertEquals(testTodo.toString(), "[T][X] Test Todo");
        testTodo.markNotDone();
        assertEquals(testTodo.toString(), "[T][ ] Test Todo");
    }

    @Test
    public void generateSaveString_unchecked() {
        Todo testTodo = new Todo("Test Todo");
        assertEquals(testTodo.generateSaveString(), "T | false | Test Todo");
    }

    @Test
    public void generateSaveString_checked() {
        Todo testTodo = new Todo("Test Todo");
        testTodo.markDone();
        assertEquals(testTodo.generateSaveString(), "T | true | Test Todo");
    }

    @Test
    public void isOccurringOnDate_false() {
        Todo testTodo = new Todo("Test Todo");
        LocalDate testDate1 = LocalDate.parse("2023-08-25");
        LocalDate testDate2 = LocalDate.parse("2099-12-31");
        assertFalse(testTodo.isOccurringOnDate(testDate1));
        assertFalse(testTodo.isOccurringOnDate(testDate2));
    }
}
