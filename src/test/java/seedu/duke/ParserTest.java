package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParseUserInput_intoCommandsObject_BYE() {
        assertEquals(Commands.BYE, new Parser().parse("bye"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_LIST() {
        assertEquals(Commands.LIST, new Parser().parse("list"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_MARK() {
        assertEquals(Commands.MARK, new Parser().parse("mark"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_UNMARK() {
        assertEquals(Commands.UNMARK, new Parser().parse("unmark"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_DELETE() {
        assertEquals(Commands.DELETE, new Parser().parse("delete"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_TODO() {
        assertEquals(Commands.TODO, new Parser().parse("todo"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_DEADLINE() {
        assertEquals(Commands.DEADLINE, new Parser().parse("deadline"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_EVENT() {
        assertEquals(Commands.EVENT, new Parser().parse("event"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_DEFAULT() {
        assertEquals(Commands.DEFAULT, new Parser().parse(""));
        assertEquals(Commands.DEFAULT, new Parser().parse("asdf"));
        assertEquals(Commands.DEFAULT, new Parser().parse(" "));
    }

}
