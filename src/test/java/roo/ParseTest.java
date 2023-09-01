package roo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParseTest {

    @Test
    public void parse_list() {
        assertEquals(Commands.LIST, Parse.parse("list"));
    }

    @Test
    public void parse_mark() {
        assertEquals(Commands.MARK, Parse.parse("mark 1"));
    }

    @Test
    public void parse_unmark() {
        assertEquals(Commands.UNMARK, Parse.parse("unmark 1"));
    }

    @Test
    public void parse_delete() {
        assertEquals(Commands.DELETE, Parse.parse("delete 1"));
    }

    @Test
    public void parse_todo() {
        assertEquals(Commands.TODO, Parse.parse("todo drink water"));
    }

    @Test
    public void parse_deadline() {
        assertEquals(Commands.DEADLINE, Parse.parse("deadline cs2103t /by 28-08-2023 12:44"));
    }

    @Test
    public void parse_event() {
        assertEquals(Commands.EVENT, Parse.parse("event st1131 /from 29-08-2023 12:34 /to 30-08-2023 12:00"));
    }

    @Test
    public void parse_check() {
        assertEquals(Commands.DATE, Parse.parse("check 29-08-2023"));
    }

    @Test
    public void parse_clear() {
        assertEquals(Commands.CLEAR, Parse.parse("clear"));
    }

    @Test
    public void parse_end() {
        assertEquals(Commands.END, Parse.parse("end"));
        assertEquals(Commands.END, Parse.parse("bye"));
    }

    @Test
    public void parse_unknown() {
        assertEquals(Commands.UNKNOWN, Parse.parse("abcdefg"));
        assertEquals(Commands.UNKNOWN, Parse.parse("hello"));
        assertEquals(Commands.UNKNOWN, Parse.parse(""));
    }

}
