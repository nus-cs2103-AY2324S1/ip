package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseUserInput_intoCommandsObject_bye() {
        assertEquals(Commands.BYE, new Parser().parse("bye"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_list() {
        assertEquals(Commands.LIST, new Parser().parse("list"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_mark() {
        assertEquals(Commands.MARK, new Parser().parse("mark"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_unmark() {
        assertEquals(Commands.UNMARK, new Parser().parse("unmark"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_delete() {
        assertEquals(Commands.DELETE, new Parser().parse("delete"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_todo() {
        assertEquals(Commands.TODO, new Parser().parse("todo"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_deadline() {
        assertEquals(Commands.DEADLINE, new Parser().parse("deadline"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_event() {
        assertEquals(Commands.EVENT, new Parser().parse("event"));
    }

    @Test
    public void testParseUserInput_intoCommandsObject_default() {
        assertEquals(Commands.DEFAULT, new Parser().parse(""));
        assertEquals(Commands.DEFAULT, new Parser().parse("asdf"));
        assertEquals(Commands.DEFAULT, new Parser().parse(" "));
    }

}
