package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.components.Parser;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidStartEndException;
import duke.exceptions.NoDescException;
import duke.exceptions.NoEndException;
import duke.exceptions.NoStartException;
import duke.stubs.StorageStub;
import duke.stubs.TaskListStub;
import duke.stubs.UiStub;
import duke.tasks.Task;

public class ParserTest {

    @Test
    public void parseEvent_noDescriptionExceptionThrown_eventCommandOnly() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event";
        String expected = "(・´з`・) Uh oh... please add a description";

        NoDescException e = assertThrows(
                NoDescException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noDescriptionExceptionThrown_whiteSpaceDesc() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event      ";
        String expected = "(・´з`・) Uh oh... please add a description";

        NoDescException e = assertThrows(
                NoDescException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noDescriptionExceptionThrown_whiteSpaceFromToCorrect() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event     /from 2023-09-09 18:00 /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add a description";

        NoDescException e = assertThrows(
                NoDescException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_invalidEventExceptionThrown_noDesc() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event/from 2023-09-09 19:00 /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... improper event format!";

        InvalidEventException e = assertThrows(
                InvalidEventException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_invalidEventExceptionThrown_noFrom() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... improper event format!";

        InvalidEventException e = assertThrows(
                InvalidEventException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noStartException_fromWithNoSpaces() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from/to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add a start date";

        NoStartException e = assertThrows(
                NoStartException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noStartException_fromWithWhiteSpaces() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from     /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add a start date";

        NoStartException e = assertThrows(
                NoStartException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noEndException_noTo() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add an end date";

        NoEndException e = assertThrows(
                NoEndException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noEndException_toWithNoSpace() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00 /to";
        String expected = "(・´з`・) Uh oh... please add an end date";

        NoEndException e = assertThrows(
                NoEndException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_noEndException_toWithWhiteSpaces() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00 /to     ";
        String expected = "(・´з`・) Uh oh... please add an end date";

        NoEndException e = assertThrows(
                NoEndException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_invalidStartEndException() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00 /to 2023-09-09 14:00";
        String expected = "(・´з`・) Uh oh... start must be after end!";

        InvalidStartEndException e = assertThrows(
                InvalidStartEndException.class, () -> parser.parseEvent(input));

        assertEquals(expected, e.getMessage());
    }

    @Test
    public void parseEvent_wrongDateFormat_words() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from now /to tmr";
        String expected = "(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format";

        try {
            String result = parser.parseEvent(input);
            assertEquals(expected, result);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseEvent_wrongDateFormat_invalidDates() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-13-21 /to 2023-13-90";
        String expected = "(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format";

        try {
            String result = parser.parseEvent(input);
            assertEquals(expected, result);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseEvent_addToListSuccessfully() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 10:00 /to 2023-09-09 14:00";
        String expectedEvent = "[E] [ ] live lecture (from: 2023-09-09 10:00 to: 2023-09-09 14:00)" + "\n";
        String expectedMessage = "(｀･ω･´)ﾉ New task added:\n" + expectedEvent
                + "Now you have 1 task in the list!";
        try {
            String result = parser.parseEvent(input);
            assertEquals(expectedMessage, result); //check message to user
            ArrayList<Task> tasks = storage.loadTasks();
            assertEquals("1. " + expectedEvent, list.listTasks(tasks)); //check storage

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
