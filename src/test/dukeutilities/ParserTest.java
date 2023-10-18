package dukeutilities;

import command.Command;
import exceptions.DukeException;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    public ParserTest() {
    }

    @Test
    public void AddCommandTodoTest() throws DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            new Parser();
            Command testCommand = Parser.parse("todo");
        });
    }

    @Test
    public void AddCommandDeadlineTest() throws DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            new Parser();
            Command testCommand = Parser.parse("deadline");
        });
    }

    @Test
    public void AddCommandEventTest() throws DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            new Parser();
            Command testCommand = Parser.parse("event");
        });
    }

    @Test
    public void InvalidInputNoDeadlineTest() throws DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            new Parser();
            Command testCommand = Parser.parse("deadline homework");
        });
    }

    @Test
    public void InvalidInputWrongFormatTest() throws DukeException {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            new Parser();
            Command testCommand = Parser.parse("deadline homework /by 2pm");
        });
    }

    @Test
    public void InvalidInputNoMeaningTest() throws DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            new Parser();
            Command testCommand = Parser.parse("efrwgwrgwrgw");
        });
    }
}
