package duke;

import command.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseUserInput_validInput_success() throws Exception {
        ArrayList<String> params = new ArrayList<>(Arrays.asList("list"));
        assertEquals(new AddCommand(params),
                Parser.parseUserInput("list"));

        params = new ArrayList<>(Arrays.asList("mark", "2"));
        assertEquals(new MarkCommand(params),
                Parser.parseUserInput("mark 2"));

        params = new ArrayList<>(Arrays.asList("unmark", "3"));
        assertEquals(new UnmarkCommand(params),
                Parser.parseUserInput("unmark 3"));

        params = new ArrayList<>(Arrays.asList("todo", "run"));
        assertEquals(new AddCommand(params),
                Parser.parseUserInput("todo run"));

        params = new ArrayList<>(Arrays.asList("deadline", "chemistry homework", "2023-10-26 15:30"));
        assertEquals(new AddCommand(params),
                Parser.parseUserInput("deadline chemistry homework /by 2023-10-26 15:30"));

        params = new ArrayList<>(Arrays.asList("event", "movie screening", "2023-11-16 18:00", "2023-11-16 20:00"));
        assertEquals(new AddCommand(params),
                Parser.parseUserInput("event movie screening /from 2023-11-16 18:00 /to 2023-11-16 20:00"));

        params = new ArrayList<>(Arrays.asList("on", "2023-08-30"));
        assertEquals(new OnCommand(params),
                Parser.parseUserInput("on 2023-08-30"));

        params = new ArrayList<>(new ArrayList<>(Arrays.asList("delete", "1")));
        assertEquals(new DeleteCommand(params),
                Parser.parseUserInput("delete 1"));
    }

    @Test
    public void parseUserInput_invalidDate_exceptionThrown() {
        try {
            ArrayList<String> params = new ArrayList<>(Arrays.asList("event", "movie screening", "2023-11-16"));
            assertEquals(new AddCommand(params),
                    Parser.parseUserInput("event movie screening /from /to 2023-11-16"));

            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Add event command format is wrong", e.getMessage());
        }

        try {
            ArrayList<String> params = new ArrayList<>(Arrays.asList("event", "movie screening", "2023-11-16"));
            assertEquals(new AddCommand(params),
                    Parser.parseUserInput("event movie screening /from 2023-11-16 /to"));

            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Add event command format is wrong", e.getMessage());
        }
    }
}
