package dialogix.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




class TodoTest {
    @Test
    void todoTest() {
        Todo test = new Todo("test");
        assertEquals("[T][X] test", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[T][O] test", test.toString(), "markAsDone() method works");
    }

    @Test
    void todoTestWithDifferentDescription() {
        Todo test = new Todo("different");
        assertEquals("[T][X] different", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[T][O] different", test.toString(), "markAsDone() method works");
    }

    @Test
    void todoTestOutputFormat() {
        Todo test = new Todo("test");
        assertEquals("T | 0 | test", test.getOutputFormat(), "getOutputFormat() method works");

        test.markAsDone();
        assertEquals("T | 1 | test", test.getOutputFormat(), "markAsDone() method works");
    }
}
