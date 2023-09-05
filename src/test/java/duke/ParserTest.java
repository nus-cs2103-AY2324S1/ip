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
        assertTrue(parser.bye());

        parser.setUserInput("not bye");
        assertFalse(parser.bye());
    }

    @Test
    void testList() {
        Parser parser = new Parser();
        parser.setUserInput("list");
        assertTrue(parser.list());

        parser.setUserInput("not list");
        assertFalse(parser.list());
    }

    @Test
    void testMark() {
        Parser parser = new Parser();
        parser.setUserInput("mark 1");
        assertTrue(parser.mark());

        parser.setUserInput("not mark");
        assertFalse(parser.mark());
    }

    @Test
    void testUnMark() {
        Parser parser = new Parser();
        parser.setUserInput("unmark 1");
        assertTrue(parser.unMark());

        parser.setUserInput("not unmark");
        assertFalse(parser.unMark());
    }

    @Test
    void testDelete() {
        Parser parser = new Parser();
        parser.setUserInput("delete 1");
        assertTrue(parser.delete());

        parser.setUserInput("not delete");
        assertFalse(parser.delete());
    }

    @Test
    void testTodo() {
        Parser parser = new Parser();
        parser.setUserInput("todo Buy groceries");
        assertTrue(parser.todo());

        parser.setUserInput("not todo");
        assertFalse(parser.todo());
    }

    @Test
    void testDeadline() {
        Parser parser = new Parser();
        parser.setUserInput("deadline Complete assignment /by 2023-09-30");
        assertTrue(parser.deadline());

        parser.setUserInput("not deadline");
        assertFalse(parser.deadline());
    }

    @Test
    void testEvent() {
        Parser parser = new Parser();
        parser.setUserInput("event Conference /from 2023-09-01 /to 2023-09-05");
        assertTrue(parser.event());

        parser.setUserInput("not event");
        assertFalse(parser.event());
    }

    @Test
    void testFind() {
        Parser parser = new Parser();
        parser.setUserInput("find task");
        assertTrue(parser.find());

        parser.setUserInput("not find");
        assertFalse(parser.find());
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
