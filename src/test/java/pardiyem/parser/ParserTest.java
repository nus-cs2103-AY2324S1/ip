package pardiyem.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pardiyem.command.AddCommand;

public class ParserTest {
    @Test
    public void parseTest1() throws NoSuchMethodException {
        assertEquals(new AddCommand("hahahahaha", 1), Parser.parseCommand("todo hahahahaha"));
    }
    @Test
    public void parseTest2() throws NoSuchMethodException {
        assertEquals(new AddCommand("hahahahaha /by 2015-02-23", 2),
                Parser.parseCommand("deadline hahahahaha /by 2015-02-23"));
    }

    @Test
    public void parseTest3() {
        try {
            Parser.parseCommand("bye hahahaha");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("You used \"hahahaha\" as an argument. A bye command shouldn't have any arguments",
                    e.getMessage());
        }
    }

    @Test
    public void parseTest4() {
        try {
            Parser.parseCommand("list hahahaha");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("You used \"hahahaha\" as an argument. A bye command shouldn't have any arguments",
                    e.getMessage());
        }
    }

    @Test
    public void parseTest5() {
        try {
            Parser.parseCommand("hahahaha");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Whoops, I do not recognize that command", e.getMessage());
        }
    }
}
