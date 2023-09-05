package alpha;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParseTest {

    private Parser parser;

    @Test
    public void addDeadline_invalidFormat_exceptionThrown() {
        try {
            parser = new Parser(new FileHandler(), new TaskList(), new UI());
            assertEquals(Deadline.makeDeadline(".", "2022-10-12 1200"),
                    (parser.addDeadline("deadline meeting /by")));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid Format!", e.getMessage());
        }
    }

    @Test
    public void addEvent_invalidFormat_exceptionThrown() {
        try {
            parser = new Parser(new FileHandler(), new TaskList(), new UI());
            assertEquals("0", parser.addEvent("event meeting /fro 2022-10-10 1200 /to 2022-10-10"));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid Format!", e.getMessage());
        }
    }

    @Test
    public void addEvent_missingInfo_exceptionThrown() {
        try {
            parser = new Parser(new FileHandler(), new TaskList(), new UI());
            assertEquals(Event.makeEvent(".", " ", "  "), (parser.addEvent("event meeting")));
        } catch (Exception e) {
            assertEquals("Text '' could not be parsed at index 0", e.getMessage());
        }
    }
}