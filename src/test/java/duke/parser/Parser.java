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
}
