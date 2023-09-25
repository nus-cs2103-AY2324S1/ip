package duke;

import duke.parsers.InputParser;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DukeTest {
    private InputParser parser = new InputParser(new ArrayList<>());

    @Test
    @Order(1)
    public void todoTest() {
        String actual = parser.parse("todo gym", false);
        String expected = "Got it. I've added this task:" + "\n" + "  [T][ ] gym #no tag" + "\n" + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void deadlineTest() {
        String actual = parser.parse("deadline assignment /by 02/05/2024 1900", false);
        String expected = "Got it. I've added this task:" + "\n" + "  [D][ ] assignment (by: 02 May 2024 7 PM) #no tag" + "\n" + "Now you have 2 tasks in the list.\n";
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void eventTest() {
        String actual = parser.parse("event soc fair /from 10/05/2023 1000 /to 11/05/2023 1000", false);
        String expected = "Got it. I've added this task:" + "\n" +
                "  [E][ ] soc fair (from: 10 May 2023 10 AM to: 11 May 2023 10 AM) #no tag" +
                "\n" + "Now you have 3 tasks in the list.\n";
        assertEquals(expected, actual);
    }
}