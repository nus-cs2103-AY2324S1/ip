package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.FishronException;
import parser.Parser;
import tasks.Deadline;
import tasks.ToDo;


public class ParserTest {
    @Test
    public void testParseTodo() {
        String description = "todo Buy milk";
        ToDo todo = Parser.parseTodo(description);

        String expectedDescription = "[T][ ] todo Buy milk";
        assertEquals(expectedDescription, todo.toString());
    }

    @Test
    public void testParseDeadline() throws FishronException {
        String description = "sleep";
        Deadline deadline = Parser.parseDeadline(description, "12-05-2023 2300");

        String expectedDescription = "[D][ ] sleep (by: May 12 2023 11:00PM)";
        assertEquals(expectedDescription, deadline.toString());
    }
}
