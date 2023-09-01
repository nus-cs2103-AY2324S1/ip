package trackerbot.utils;

import org.junit.jupiter.api.Test;
import trackerbot.command.CommandType;
import trackerbot.exception.TrackerBotException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseAdd_todoCorrectInput_shouldParse() throws TrackerBotException {
        assertEquals("[T][ ] Test Input",
                Parser.parseAdd(CommandType.TODO, "Test Input").toString());
    }

    @Test
    public void parseAdd_todoEmptyInput_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.TODO, "");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Cannot track task without description.", e.getMessage());
        }
    }

    @Test
    public void parseAdd_deadlineCorrectInput_shouldParse() throws TrackerBotException {
        assertEquals("[D][ ] Test Input (by: 12 Dec 2000, Tue @ 12:00AM)",
                Parser.parseAdd(CommandType.DEADLINE, "Test Input /by 12/12/2000").toString());
    }

    @Test
    public void parseAdd_deadlineMissingFlag_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.DEADLINE, "Empty by flag");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Improper format: deadline [description] /by [end-date]", e.getMessage());
        }
    }

    @Test
    public void parseAdd_deadlineEmptyInput_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.DEADLINE, "");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Improper format: deadline [description] /by [end-date]", e.getMessage());
        }
    }

    @Test
    public void parseAdd_deadlineExtraFlag_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.DEADLINE, "Test Input /by 12/12/2000 /by additional field");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Too many flags: deadline [description] /by [end-date]", e.getMessage());
        }
    }

    @Test
    public void parseAdd_eventCorrectInput_shouldParse() throws TrackerBotException {
        assertEquals("[E][ ] Test Input (from: 01 Dec 2000, Fri @ 12:00AM | to: 09 Dec 2000, Sat @ 12:00AM)",
                Parser.parseAdd(CommandType.EVENT, "Test Input /from 1/12/2000 /to 9/12/2000").toString());
    }

    @Test
    public void parseAdd_eventMissingFlag_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.EVENT, "Test Input /from 1/12/2000");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Improper format: event [description] /from [start-date] /to [end-date]",
                    e.getMessage());
        }
    }

    @Test
    public void parseAdd_eventEmptyInput_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.EVENT, "");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Improper format: event [description] /from [start-date] /to [end-date]",
                    e.getMessage());
        }
    }

    @Test
    public void parseAdd_eventFlippedFlag_shouldThrow() {
        try {
            Parser.parseAdd(CommandType.EVENT, "Test Input /to 1/12/2000 /from 9/12/2000");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Improper format: event [description] /from [start-date] /to [end-date]",
                    e.getMessage());
        }

    }

    @Test
    public void parseAdd_illegalState_shouldThrow() throws TrackerBotException {
        try {
            Parser.parseAdd(CommandType.UNKNOWN, "");
            fail();
        } catch (IllegalStateException e) {
            // There should be an Illegal State Exception - this is not caught by the program.
        }
    }
}
