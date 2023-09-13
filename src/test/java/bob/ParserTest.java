package bob;

import bob.command.AddCommand;
import bob.command.Command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parse() {
        Command c = new AddCommand("todo read");
        assertEquals(c.getClass(), Parser.parse("todo read").getClass());
    }
}

