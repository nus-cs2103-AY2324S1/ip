package duke.parser;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
class ParserTest {

    Parser parser = new Parser();

    @Test
    public void listRegex_success() {
        assertEquals(true, parser.listRegex.matcher("list deez nuts").find());
    }

    @Test
    public void findRegex_success() {
        assertEquals(true, parser.findRegex.matcher("find deez nuts").find());
    }
}
