package zean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import zean.exception.DukeException;

public class ParserTest {
    @Test
    public void invalidCommandTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("hey", new TaskList()));
        assertEquals("\tOOPS!!! I'm sorry, but I don't understand what that means :-(",
                exception.getMessage());
    }

    @Test
    public void invalidCommandTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("kjshdkafhk", new TaskList()));
        assertEquals("\tOOPS!!! I'm sorry, but I don't understand what that means :-(",
                exception.getMessage());
    }

    @Test
    public void invalidMarkTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("mark ", new TaskList()));
        assertEquals("\tPlease provide the task number.",
                exception.getMessage());
    }

    @Test
    public void invalidMarkTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("mark aas", new TaskList()));
        assertEquals("\tPlease provide a valid task number.",
                exception.getMessage());
    }

    @Test
    public void invalidUnmarkTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("unmark ", new TaskList()));
        assertEquals("\tPlease provide the task number.",
                exception.getMessage());
    }

    @Test
    public void invalidUnmarkTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("unmark aas", new TaskList()));
        assertEquals("\tPlease provide a valid task number.",
                exception.getMessage());
    }

    @Test
    public void invalidDeleteTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("delete ", new TaskList()));
        assertEquals("\tPlease provide the task number.",
                exception.getMessage());
    }

    @Test
    public void invalidDeleteTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("delete aas", new TaskList()));
        assertEquals("\tPlease provide a valid task number.",
                exception.getMessage());
    }

    @Test
    public void emptyTodoTest() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("todo ", new TaskList()));
        assertEquals("\tHmm, the description of a todo cannot be empty :(",
                exception.getMessage());
    }

    @Test
    public void emptyDeadlineDescriptionTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("deadline /by 2", new TaskList()));
        assertEquals("\tHmm, the description of a deadline cannot be empty :(",
                exception.getMessage());
    }

    @Test
    public void emptyDeadlineDescriptionTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("deadline ", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the deadline.\n\tUse \"/by\" to do so.",
                exception.getMessage());
    }

    @Test
    public void emptyDeadlineDateTest() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("deadline abc /by", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the deadline.",
                exception.getMessage());
    }

    @Test
    public void wrongDeadlineDateTest() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("deadline abc /by abc", new TaskList()));
        assertEquals("\tHmm, I don't understand the date. Use this format: YYYY-MM-DD",
                exception.getMessage());
    }

    @Test
    public void emptyEventDescriptionTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event /to 2", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the starting date."
                        + "\n\tUse \"/from\" to do so.",
                exception.getMessage());
    }

    @Test
    public void emptyEventDescriptionTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event /from ", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the ending date.\n\tUse \"/to\" to do so.",
                exception.getMessage());
    }

    @Test
    public void emptyEventDescriptionTest3() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event /from /to", new TaskList()));
        assertEquals("\tHmm, the description of an event cannot be empty :(",
                exception.getMessage());
    }

    @Test
    public void emptyEventDescriptionTest4() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event /from 2023-08-29 /to", new TaskList()));
        assertEquals("\tHmm, the description of an event cannot be empty :(",
                exception.getMessage());
    }

    @Test
    public void emptyEventDescriptionTest5() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event /from /to 2023-08-29", new TaskList()));
        assertEquals("\tHmm, the description of an event cannot be empty :(",
                exception.getMessage());
    }

    @Test
    public void emptyEventDescriptionTest6() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event /from 2023-08-28 /to 2023-08-29", new TaskList()));
        assertEquals("\tHmm, the description of an event cannot be empty :(",
                exception.getMessage());
    }

    @Test
    public void emptyEventDateTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event afgd /from /to", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the starting date.",
                exception.getMessage());
    }

    @Test
    public void emptyEventDateTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event afgd /from /to 2023-08-29", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the starting date.",
                exception.getMessage());
    }

    @Test
    public void emptyEventDateTest3() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event afgd /from 2023-08-29 /to", new TaskList()));
        assertEquals("\tOOPS!!! You forgot to specify the ending date.",
                exception.getMessage());
    }

    @Test
    public void wrongEventDateTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event afsdf /from a /to a", new TaskList()));
        assertEquals("\tHmm, I don't understand the date. Use this format: YYYY-MM-DD",
                exception.getMessage());
    }

    @Test
    public void wrongEventDateTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event afsdf /from 2023-08-29 /to a", new TaskList()));
        assertEquals("\tHmm, I don't understand the date. Use this format: YYYY-MM-DD",
                exception.getMessage());
    }

    @Test
    public void wrongEventDateTest3() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parse("event afsdf /from g /to 2023-08-29", new TaskList()));
        assertEquals("\tHmm, I don't understand the date. Use this format: YYYY-MM-DD",
                exception.getMessage());
    }
}
