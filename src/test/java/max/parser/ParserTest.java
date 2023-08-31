package max.parser;

import max.exception.MaxException;
import max.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void todo_emptyDescription_exceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.parse("todo ");
        } catch (MaxException e) {
            assertEquals("     Watch out -- todo description cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void deadline_emptyByDate_exceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.parse("deadline /by");
        } catch (MaxException e) {
            assertEquals("     Oops... Deadline item or 'by' date cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void event_noDateFlag_exceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.parse("event cook dinner");
        } catch (MaxException e) {
            assertEquals("     Hey! Event must contain '/from' and '/to' tags.", e.getMessage());
        }
    }
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.parse("yolo");
        } catch (MaxException e) {
            assertEquals("Invalid command sir.", e.getMessage());
        }
    }
}
