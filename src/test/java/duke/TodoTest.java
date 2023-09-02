package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void testTodoStringConversion() {
        Todo test = new Todo("Todo Test");
        assertEquals("[T] [ ] Todo Test", test.toString());

        test.markAsDone();
        assertEquals("[T] [X] Todo Test", test.toString());

        test.markAsUndone();
        assertEquals("[T] [ ] Todo Test", test.toString());
    }

    @Test
    public void testTodoSavedStringConversion() {
        Todo test = new Todo("Todo Test Saved");
        assertEquals("[T] // //Todo Test Saved//", test.convertToSavedString());

        test.markAsDone();
        assertEquals("[T] //X//Todo Test Saved//", test.convertToSavedString());

        test.markAsUndone();
        assertEquals("[T] // //Todo Test Saved//", test.convertToSavedString());
    }
}
