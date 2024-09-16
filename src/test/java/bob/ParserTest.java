package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.BobException;

public class ParserTest {
    @Test
    public void testIsMark1() {
        Parser parser = new Parser();
        boolean test = parser.isMark("mark 1");
        assertEquals(true, test);
    }

    @Test
    public void testIsMark2() {
        Parser parser = new Parser();
        boolean test = parser.isMark("mark this");
        assertEquals(false, test);
    }

    @Test
    public void testIsDelete1() {
        Parser parser = new Parser();
        boolean test = parser.isDelete("delete 1");
        assertEquals(true, test);
    }

    @Test
    public void testIsDelete2() {
        Parser parser = new Parser();
        boolean test = parser.isDelete("delete this");
        assertEquals(false, test);
    }

    @Test
    public void testFindKeyword() {
        Parser parser = new Parser();
        try {
            String test = parser.findKeyword("find HAHGDKWYkydhgwakyuj");
            assertEquals("HAHGDKWYkydhgwakyuj", test);
        } catch (BobException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testGetNewStartDate() {
        Parser parser = new Parser();
        String test = parser.getNewStartDate("reschedule this /from 2023-09-09 /to 2023-10-10");
        assertEquals("2023-09-09", test);
    }

    @Test
    public void testGetNewEndDate() {
        Parser parser = new Parser();
        String test = parser.getNewEndDate("reschedule this /from 2023-09-09 /to 2023-10-10");
        assertEquals("2023-10-10", test);
    }
}
