package chatbot;

import chatbot.exceptions.*;
import chatbot.tasks.EventTask;
import chatbot.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void markTest1() {
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
    public void markTest2() {
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
    public void markTest3() {
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
    public void deleteTest1() {
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
    public void deleteTest2() {
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
    public void deleteTest3() {
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
    public void eventTest1() {
        String command = "event /from 1 /to 2";
        try {
            Parser.parseEventTaskCommand(command);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof EventMissingFieldException);
        }
    }

    @Test
    public void eventTest2() {
        String command = "event 1 /from /to 2";
        try {
            Parser.parseEventTaskCommand(command);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof EventMissingFieldException);
        }
    }

    @Test
    public void eventTest3() {
        String command = "event 1 /from 2 /to";
        try {
            Parser.parseEventTaskCommand(command);
            fail();
        } catch (MissingFieldException e) {
            assertTrue(e instanceof EventMissingFieldException);
        }
    }

    @Test
    public void eventTest4() {
        String command = "event 1 /from 2 /to 3";
        try {
            Task task = Parser.parseEventTaskCommand(command);
            assertTrue(task instanceof EventTask);
        } catch (MissingFieldException e) {
            fail();
        }
    }

    @Test
    public void eventTest5() {
        String command = "event 1 /from 2 /to 3";
        try {
            Task task = Parser.parseEventTaskCommand(command);
            assertEquals(task.getName(), "1");
        } catch (MissingFieldException e) {
            fail();
        }
    }
}