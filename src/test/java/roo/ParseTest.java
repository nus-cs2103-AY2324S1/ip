package roo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import roo.commands.*;

public class ParseTest {

    @Test
    public void parse_list() {
        assertEquals(Command.LIST, Parse.parse("list"));
    }

    @Test
    public void parse_mark() {
        assertEquals(Command.MARK, Parse.parse("mark 1"));
    }

    @Test
    public void parse_unmark() {
        assertEquals(Command.UNMARK, Parse.parse("unmark 1"));
    }

    @Test
    public void parse_delete() {
        assertEquals(Command.DELETE, Parse.parse("delete 1"));
    }

    @Test
    public void parse_todo() {
        assertEquals(Command.TODO, Parse.parse("todo drink water"));
    }

    @Test
    public void parse_deadline() {
        assertEquals(Command.DEADLINE, Parse.parse("deadline cs2103t /by 28-08-2023 12:44"));
    }

    @Test
    public void parse_event() {
        assertEquals(Command.EVENT, Parse.parse("event st1131 /from 29-08-2023 12:34 /to 30-08-2023 12:00"));
    }

    @Test
    public void parse_check() {
        assertEquals(Command.DATE, Parse.parse("check 29-08-2023"));
    }

    @Test
    public void parse_clear() {
        assertEquals(Command.CLEAR, Parse.parse("clear"));
    }

    @Test
    public void parse_end() {
        assertEquals(Command.END, Parse.parse("end"));
        assertEquals(Command.END, Parse.parse("bye"));
    }

    @Test
    public void parse_unknown() {
        assertEquals(Command.UNKNOWN, Parse.parse("abcdefg"));
        assertEquals(Command.UNKNOWN, Parse.parse("hello"));
        assertEquals(Command.UNKNOWN, Parse.parse(""));
    }

}
