package bruno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bruno.exceptions.BrunoException;
import bruno.exceptions.BrunoUnknownTaskException;

public class ParserTest {

    private Parser parser;

    @BeforeEach void setUp() {
        parser = new Parser(new TaskList(new Storage("xxx", "yyy"), new UI()));
    }

    @Test void testParse_normalInput_correctOutputGenerated() {
        try {
            String task = "todo work";
            assertEquals("Woof. I have added this task:\n[T][ ] work\nNow you have 1 task in your tasks.",
                    parser.parseInput(task));
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test void testParse_byeTask_falseReturned() {
        try {
            String task = "bye";
            assertEquals("bye", parser.parseInput(task));
        } catch (BrunoException e) {
            fail();
        }
    }

    @Test void testParse_unknownTask_exceptionThrown() {
        assertThrows(BrunoUnknownTaskException.class, () -> parser.parseInput("show tasks"));
    }
}
