package rua.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import rua.command.AddCommand;
import rua.command.Command;
import rua.task.Deadline;
import rua.task.Event;
import rua.task.Todo;

public class ParserTest {
    @Test
    public void parseStringTest() throws Exception {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("#fun");
        Command expectedAddTodoWithTag = new AddCommand(new Todo("play", false, tags));
        Command expectedAddDeadline = new AddCommand(new Deadline("quiz", LocalDate.parse("2020-12-12")));
        Command expectedAddEvent = new AddCommand(new Event("project", LocalDate.parse("2020-12-10"),
                LocalDate.parse("2020-12-31")));
        assertEquals(expectedAddTodoWithTag, Parser.parse("todo play #fun"));
        assertEquals(expectedAddDeadline, Parser.parse("deadline quiz /by 2020-12-12"));
        assertEquals(expectedAddEvent, Parser.parse("event project /from 2020-12-10 /to 2020-12-31"));
    }

    @Test
    public void parseStringTestExceptionThrown() {
        try {
            assertEquals(null, Parser.parse("todo"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(" OOPS!!! The description of a Todo cannot be empty.\n", e.toString());
        }
    }
}
