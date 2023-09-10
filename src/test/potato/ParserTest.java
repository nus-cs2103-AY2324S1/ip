package potato;

import potato.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void ParserTest1() {
        Command c = Parser.parse("bye");
        assertEquals(c, new ExitCommand());
    }
}
