package dukeutilities;

import command.Command;

import exceptions.DukeException;

import task.ToDo;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void AddCommandTodoTest() throws DukeException {
        assertThrows(DukeException.class, () -> {
            Parser test = new Parser();
            Command testCommand = Parser.parse("todo");
        });
    }

    @Test
    public void AddCommandDeadlineTest() throws DukeException {
        assertThrows(DukeException.class, () -> {
            Parser test = new Parser();
            Command testCommand = Parser.parse("deadline");
        });
    }

    @Test
    public void AddCommandEventTest() throws DukeException {
        assertThrows(DukeException.class, () -> {
            Parser test = new Parser();
            Command testCommand = Parser.parse("event");
        });
    }

    @Test
    public void InvalidInputNoDeadlineTest() throws DukeException {
        assertThrows(DukeException.class, () -> {
            Parser test = new Parser();
            Command testCommand = Parser.parse("deadline homework");
        });
    }

    @Test
    public void InvalidInputWrongFormatTest() throws DukeException {
        assertThrows(DateTimeParseException.class, () -> {
            Parser test = new Parser();
            Command testCommand = Parser.parse("deadline homework /by 2pm");
        });
    }

    @Test
    public void InvalidInputNoMeaningTest() throws DukeException {
        assertThrows(DukeException.class, () -> {
            Parser test = new Parser();
            Command testCommand = Parser.parse("efrwgwrgwrgw");
        });
    }

}
