package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
class ParserTest {

    private Parser parser = new Parser();

    @Test
    public void listRegex_success() {
        assertEquals(true, parser.getListRegex().matcher("list deez nuts").find());
    }

    @Test
    public void findRegex_success() {
        assertEquals(true, parser.getFindRegex().matcher("find deez nuts").find());
    }


    @Test
    public void unmarkRegex_success() {
        assertEquals(true, parser.getUnmarkRegex().matcher("unmark 1").find());
    }

    @Test
    public void markRegex_success() {
        assertEquals(true, parser.getMarkRegex().matcher("mark 1").find());
    }

    @Test
    public void deadlineRegex_success() {
        assertEquals(true, parser.getDeadlineRegex().matcher("deadline deez nuts /by tmr").find());
    }

    @Test
    public void todoRegex_success() {
        assertEquals(true, parser.getTodoRegex().matcher("todo deez nuts").find());
    }

    @Test
    public void eventRegex_success() {
        assertEquals(true, parser.getEventRegex().matcher("event deez nuts /from now /to later").find());
    }

    @Test
    public void deleteRegex_success() {
        assertEquals(true, parser.getDeleteRegex().matcher("delete 1").find());
    }


}
