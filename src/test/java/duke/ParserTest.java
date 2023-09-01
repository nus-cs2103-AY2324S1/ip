package duke;

import command.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseUserInput_validInput_success() throws Exception {
        assertEquals(new ArrayList<>(Arrays.asList("list")),
                Parser.parseUserInput("list"));

        assertEquals(new ArrayList<>(Arrays.asList("mark", "2")),
                Parser.parseUserInput("mark 2"));

        assertEquals(new ArrayList<>(Arrays.asList("unmark", "3")),
                Parser.parseUserInput("unmark 3"));

        assertEquals(new ArrayList<>(Arrays.asList("todo", "run")),
                Parser.parseUserInput("todo run"));

        assertEquals(new ArrayList<>(Arrays.asList("deadline", "chemistry homework", "2023-10-26 15:30")),
                Parser.parseUserInput("deadline chemistry homework /by 2023-10-26 15:30"));

        assertEquals(new ArrayList<>(Arrays.asList("event", "movie screening", "2023-11-16 18:00", "2023-11-16 20:00")),
                Parser.parseUserInput("event movie screening /from 2023-11-16 18:00 /to 2023-11-16 20:00"));

        assertEquals(new ArrayList<>(Arrays.asList("on", "2023-08-30")),
                Parser.parseUserInput("on 2023-08-30"));

        assertEquals(new ArrayList<>(Arrays.asList("delete", "1")),
                Parser.parseUserInput("delete 1"));
    }

    @Test
    public void parseUserInput_invalidDate_exceptionThrown() {
        try {
            assertEquals(new ArrayList<>(Arrays.asList("event", "movie screening", "2023-11-16")),
                    Parser.parseUserInput("event movie screening /from /to 2023-11-16"));

            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid input for date", e.getMessage());
        }

        try {
            assertEquals(new ArrayList<>(Arrays.asList("event", "movie screening", "2023-11-16")),
                    Parser.parseUserInput("event movie screening /from 2023-11-16 /to"));

            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid input for date", e.getMessage());
        }
    }
}
