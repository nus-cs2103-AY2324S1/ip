package parser;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
/*
  @Test
    public void intDivision_nonZeroDivisor_success() throws Exception {
        // normal division results in an integer answer 2
        assertEquals(2, new IntPair(4, 2).intDivision());

        // normal division results in a decimal answer 1.9
        assertEquals(1, new IntPair(19, 10).intDivision());

        // dividend is zero but devisor is not
        assertEquals(0, new IntPair(0, 5).intDivision());
    }
        @Test
    public void intDivision_zeroDivisor_exceptionThrown() {
        try {
            assertEquals(0, new IntPair(1, 0).intDivision());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Divisor is zero", e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        assertEquals("4,7", new IntPair(4, 7).toString());
    }
*/

    @Test
    public void getCommand_invalidCommandNotEmpty_throwDukeException(){
        try {
            assertEquals(" ", new Parser().getCommand("sdfsfsdf"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandTodo_throwDukeException(){
        try {
            assertEquals(" ", new Parser().getCommand("todooo"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandDl_throwDukeException(){
        try {
            assertEquals(" ", new Parser().getCommand("deadlinesad"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandEvent_throwDukeException(){
        try {
            assertEquals(" ", new Parser().getCommand("eventsdf"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }


    @Test
    public void getCommand_invalidCommandEmpty_throwDukeException(){
        try {
            assertEquals(" ", new Parser().getCommand(""));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

}
