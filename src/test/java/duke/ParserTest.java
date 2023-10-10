package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @AfterEach
    public void tearDown() {
        parser.goodbye();
    }

    @Test
    public void testIsBye() {
        // Input the "bye" command
        provideInput("bye");

        assertTrue(parser.isBye());
    }

    @Test
    public void testIsList() {
        // Input the "list" command
        provideInput("list");

        assertTrue(parser.isList());
    }

    @Test
    public void testIsMark() {
        // Input a mark command
        provideInput("mark 1");

        assertTrue(parser.isMark());
    }

    @Test
    public void testIsUnmark() {
        // Input an unmark command
        provideInput("unmark 1");

        assertTrue(parser.isUnmark());
    }

    @Test
    public void testIsDelete() {
        // Input a delete command
        provideInput("delete 1");

        assertTrue(parser.isDelete());
    }

    @Test
    public void testIsTodo() {
        // Input a todo command
        provideInput("todo Buy groceries");

        assertTrue(parser.isTodo());
    }

    @Test
    public void testIsDeadline() {
        // Input a deadline command
        provideInput("deadline Finish report /by 2023-09-30");

        assertTrue(parser.isDeadline());
    }

    @Test
    public void testIsEvent() {
        // Input an event command
        provideInput("event Birthday party /from 2023-10-15 /to 2023-10-16");

        assertTrue(parser.isEvent());
    }

    @Test
    public void testIsFind() {
        // Input a find command
        provideInput("find task");

        assertTrue(parser.isFind());
    }

    @Test
    public void testIsSort() {
        // Input the "sort" command
        provideInput("sort");

        assertTrue(parser.isSort());
    }

    // Helper method to provide input for testing
    private void provideInput(String input) {
        InputStream originalInput = System.in;
        try {
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            parser.setUserInput(input); // Set the user input directly
        } finally {
            System.setIn(originalInput);
        }
    }
}
