package kevin.parser;

import kevin.exception.KevinException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileParserTest {
    @Test
    public void parseLine_invalidCommand_exceptionThrown() {
        String input = "invalidCommand";
        try {
            FileParser fileParser = new FileParser();
            fileParser.parseLine(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }

    @Test
    public void parseLine_invalidToDoInput_exceptionThrown() {
        String input = "Todo - ";
        try {
            FileParser fileParser = new FileParser();
            fileParser.parseLine(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }

    @Test
    public void parseLine_invalidDeadlineInput_exceptionThrown() {
        String input = "Deadline - cs2103 - today";
        try {
            FileParser fileParser = new FileParser();
            fileParser.parseLine(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }

    @Test
    public void parseLine_invalidEventInput_exceptionThrown() {
        String input = "Event - lecture - 2pm";
        try {
            FileParser fileParser = new FileParser();
            fileParser.parseLine(input);
        } catch (Exception ex) {
            assertTrue(ex instanceof KevinException);
        }
    }
}
