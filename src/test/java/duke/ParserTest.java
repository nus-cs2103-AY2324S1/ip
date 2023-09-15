package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

class ParserTest {
    private final InputStream originalSystemIn = System.in;
    private ByteArrayInputStream testInput;

    @BeforeEach
    void setUp() {
        String input = "todo Buy groceries";
        testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void testGetUserInput() {
        Parser parser = new Parser();
        String userInput = parser.getUserInput();
        assertEquals("todo Buy groceries", userInput);
    }

    @Test
    void testBye() {
        Parser parser = new Parser();
        parser.setUserInput("bye");
        assertTrue(parser.isBye());

        parser.setUserInput("not bye");
        assertFalse(parser.isBye());
    }

    @Test
    void testList() {
        Parser parser = new Parser();
        parser.setUserInput("list");
        assertTrue(parser.isList());

        parser.setUserInput("not list");
        assertFalse(parser.isList());
    }

    @Test
    void testMark() {
        Parser parser = new Parser();
        parser.setUserInput("mark 1");
        assertTrue(parser.isMark());

        parser.setUserInput("not mark");
        assertFalse(parser.isMark());
    }

    @Test
    void testUnMark() {
        Parser parser = new Parser();
        parser.setUserInput("unmark 1");
        assertTrue(parser.isUnmark());

        parser.setUserInput("not unmark");
        assertFalse(parser.isUnmark());
    }

    @Test
    void testDelete() {
        Parser parser = new Parser();
        parser.setUserInput("delete 1");
        assertTrue(parser.isDelete());

        parser.setUserInput("not delete");
        assertFalse(parser.isDelete());
    }

    @Test
    void testTodo() {
        Parser parser = new Parser();
        parser.setUserInput("todo Buy groceries");
        assertTrue(parser.isTodo());

        parser.setUserInput("not todo");
        assertFalse(parser.isTodo());
    }

    @Test
    void testDeadline() {
        Parser parser = new Parser();
        parser.setUserInput("deadline Complete assignment /by 2023-09-30");
        assertTrue(parser.isDeadline());

        parser.setUserInput("not deadline");
        assertFalse(parser.isDeadline());
    }

    @Test
    void testEvent() {
        Parser parser = new Parser();
        parser.setUserInput("event Conference /from 2023-09-01 /to 2023-09-05");
        assertTrue(parser.isEvent());

        parser.setUserInput("not event");
        assertFalse(parser.isEvent());
    }

    @Test
    void testFind() {
        Parser parser = new Parser();
        parser.setUserInput("find task");
        assertTrue(parser.isFind());

        parser.setUserInput("not find");
        assertFalse(parser.isFind());
    }

    @Test
    void testGoodbye() {
        Parser parser = new Parser();
        parser.goodbye();

        // Ensure the scanner has been closed
        try {
            Scanner scanner = (Scanner) Parser.class.getDeclaredField("scanner").get(parser);
            assertFalse(scanner.hasNext());
        } catch (NoSuchFieldException | IllegalAccessException | IllegalStateException e) {
        }
    }
}
