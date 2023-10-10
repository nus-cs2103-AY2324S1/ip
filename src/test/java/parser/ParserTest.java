package parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import data.Save;
import duke.DukeException;

public class ParserTest {
    private final Save testSave = new Save();
    private final Parser parser = new Parser(testSave);

    @Test
    public void testIssueCommand_markWithoutNumber() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.issueCommand("mark");
        });
        assertTrue(exception.getMessage().contains(" Provide the task number."));
    }

    @Test
    public void testIssueCommand_markInvalidNumber() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.issueCommand("mark abc");
        });
        assertTrue(exception.getMessage().contains(" Hmmm, your input format for marking tasks is incorrect;\n"
                + " Format: mark [int task number > 0]"));
    }

    @Test
    public void testIssueCommand_deleteWithoutNumber() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.issueCommand("delete");
        });
        assertTrue(exception.getMessage().contains(" Provide a task number. "
                + "Format: delete [int task number > 0]"));
    }

    @Test
    public void testIssueCommand_deleteInvalidNumber() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.issueCommand("delete xyz");
        });
        assertTrue(exception.getMessage().contains(" Hmmm, your input format for deleting tasks is incorrect;\n"
                + " Format: delete [int task number > 0]"));
    }

    @Test
    public void testIssueCommand_scheduleWithoutDate() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.issueCommand("schedule");
        });
        assertTrue(exception.getMessage().contains(" Provide the date in the format:\n"
                + " schedule /on dd/MM/yyyy"));
    }

    @Test
    public void testIssueCommand_findWithoutKeyword() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.issueCommand("find");
        });
        assertTrue(exception.getMessage().contains(" Keyword. I NEED A KEYWORD!!! "));
    }
}
