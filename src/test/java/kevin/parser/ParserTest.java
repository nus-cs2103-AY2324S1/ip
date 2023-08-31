package kevin.parser;

import kevin.exception.KevinException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void prepareArguments_invalidCommand_exceptionThrown() {
        String input = "invalidCommand";
        try {
            Parser parser = new Parser();
            parser.prepareArguments(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }

    @Test
    public void prepareArguments_invalidToDoInput_exceptionThrown() {
        String input = "todo ";
        try {
            Parser parser = new Parser();
            parser.prepareArguments(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }

    @Test
    public void prepareArguments_invalidDeadlineInput_exceptionThrown() {
        String input = "deadline cs2103 by today";
        try {
            Parser parser = new Parser();
            parser.prepareArguments(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }

    @Test
    public void prepareArguments_invalidEventInput_exceptionThrown() {
        String input = "event lecture /from 2pm";
        try {
            Parser parser = new Parser();
            parser.prepareArguments(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }
}
