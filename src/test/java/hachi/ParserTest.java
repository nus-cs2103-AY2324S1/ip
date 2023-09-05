package hachi;

import exceptions.HachiException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseTaskList_validEvent_success() {
        String event = "E | 1 | birthday | 2023-04-05 | 2023-04-06";
        List<String> events = new ArrayList<>();
        events.add(event);
        try {
            assertEquals(Parser.parseTaskList(events).toString(),
                    "1. [E][X] birthday (from: Apr 05 2023 to: Apr 06 2023)");
        } catch (HachiException e) {
            fail();
        }
    }

    @Test
    public void parseTaskList_invalidEvent_fail() {
        String event = "E | 1 | birthday | 2023-04-05 | 2023-04-03";
        List<String> events = new ArrayList<>();
        events.add(event);
        try {
            assertEquals(Parser.parseTaskList(events).toString(),
                    "1. [E][X] birthday (from: Apr 05 2023 to: Apr 03 2023)");
            fail();
        } catch (HachiException e) {
            assertEquals("Your end date (2023-04-03) is before your start date (2023-04-05). " +
                            "Are you sure you entered them in the right order?", e.getMessage());
        }
    }

    @Test
    public void parseTaskList_invalidTaskFormat_fail() {
        String event = "D | 1 | birthday | 2023-04-05 | 2023-04-03";
        List<String> events = new ArrayList<>();
        events.add(event);
        try {
            assertEquals(Parser.parseTaskList(events).toString(),
                    "1. [D][X] birthday (by: Apr 05 2023)");
            fail();
        } catch (HachiException e) {
            assertEquals("Deadline stored in the wrong format! Please check the file at 'data/tasks.txt'",
                    e.getMessage());
        }
    }

}
