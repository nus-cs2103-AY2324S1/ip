package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatbot.exceptions.DeleteMissingFieldException;
import chatbot.exceptions.EventMissingFieldException;
import chatbot.exceptions.InvalidTaskIndexException;
import chatbot.exceptions.MarkMissingFieldException;
import chatbot.exceptions.MissingFieldException;
import chatbot.tasks.EventTask;
import chatbot.tasks.Task;
import javafx.util.Pair;

public class ParserTest {
    @Test
    public void parseMarkCommand_missingField_exception() {
        String[] commandWords = new String[]{"mark"};
        try {
            Parser.parseMarkCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof MarkMissingFieldException);
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parseMarkCommand_validIndex_noException() {
        String[] commandWords = new String[]{"mark", "2103"};
        try {
            assertEquals(Parser.parseMarkCommand(commandWords), 2103);
        } catch (MissingFieldException e) {
            fail();
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parseMarkCommand_invalidIndex_exception() {
        String[] commandWords = new String[]{"mark", "a1"};
        try {
            Parser.parseMarkCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            fail();
        } catch (InvalidTaskIndexException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parseDeleteCommand_missingField_exception() {
        String[] commandWords = new String[]{"delete"};
        try {
            Parser.parseDeleteCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof DeleteMissingFieldException);
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parseDeleteCommand_validInput_noException() {
        String[] commandWords = new String[]{"delete", "2103"};
        try {
            assertEquals(Parser.parseDeleteCommand(commandWords), 2103);
        } catch (MissingFieldException e) {
            fail();
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parseDeleteCommand_invalidIndex_exception() {
        String[] commandWords = new String[]{"delete", "a1"};
        try {
            Parser.parseDeleteCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            fail();
        } catch (InvalidTaskIndexException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parsePriorityCommand_missingTwoFields_exception() {
        String[] commandWords = new String[]{"priority"};
        try {
            Parser.parsePriorityCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(true);
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parsePriorityCommand_missingOneField_exception() {
        String[] commandWords = new String[]{"priority", "1"};
        try {
            Parser.parsePriorityCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(true);
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parsePriorityCommand_extraField_exception() {
        String[] commandWords = new String[]{"priority", "1", "H", "M"};
        try {
            Parser.parsePriorityCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(true);
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parsePriorityCommand_invalidIndex_exception() {
        String[] commandWords = new String[]{"priority", "a1", "H"};
        try {
            Parser.parsePriorityCommand(commandWords);
            fail();
        } catch (MissingFieldException e) {
            fail();
        } catch (InvalidTaskIndexException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parsePriorityCommand_validInput_success() {
        String[] commandWords = new String[]{"priority", "1", "H"};
        try {
            Pair output = Parser.parsePriorityCommand(commandWords);
            assertEquals(new Pair<>(1, "H"), output);
        } catch (MissingFieldException e) {
            fail();
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void parseEventCommand_missingDescription_exception() {
        String command = "event /from 1 /to 2";
        try {
            Parser.parseEventTaskCommand(command);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof EventMissingFieldException);
        }
    }

    @Test
    public void parseEventCommand_missingFrom_exception() {
        String command = "event 1 /from /to 2";
        try {
            Parser.parseEventTaskCommand(command);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof EventMissingFieldException);
        }
    }

    @Test
    public void parseEventCommand_missingTo_exception() {
        String command = "event 1 /from 2 /to";
        try {
            Parser.parseEventTaskCommand(command);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof EventMissingFieldException);
        }
    }

    @Test
    public void parseEventCommand_validInput_success() {
        String command = "event 1 /from 2 /to 3";
        try {
            Task task = Parser.parseEventTaskCommand(command);
            assertTrue(task instanceof EventTask);
        } catch (MissingFieldException e) {
            fail();
        }
    }

    @Test
    public void parseEventCommand_validInput_correctName() {
        String command = "event 1 /from 2 /to 3";
        try {
            Task task = Parser.parseEventTaskCommand(command);
            assertEquals(task.getName(), "1");
        } catch (MissingFieldException e) {
            fail();
        }
    }
}
