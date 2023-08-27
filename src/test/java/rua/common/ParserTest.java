package rua.common;

import rua.command.*;
import rua.task.Deadline;
import rua.task.Event;
import rua.task.Todo;
import rua.exception.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void ParseStringTest() throws Exception{
        Command expectedAddTodo = new AddCommand(new Todo("play"));
        Command expectedAddDeadline = new AddCommand(new Deadline("quiz", LocalDate.parse("2020-12-12")));
        Command expectedAddEvent = new AddCommand(new Event("project", LocalDate.parse("2020-12-10"), LocalDate.parse("2020-12-31")));
        assertEquals(expectedAddTodo, Parser.parse("todo play"));
        assertEquals(expectedAddDeadline, Parser.parse("deadline quiz /by 2020-12-12"));
        assertEquals(expectedAddEvent, Parser.parse("event project /from 2020-12-10 /to 2020-12-31"));
    }

    @Test
    public void ParseStringTestExceptionThrown(){
        try {
            assertEquals(null, Parser.parse("todo"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(" OOPS!!! The description of a Todo cannot be empty.\n", e.toString());
        }
    }
}