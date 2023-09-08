package duke;

import duke.exceptions.*;
import duke.stubs.StorageStub;
import duke.stubs.TaskListStub;
import duke.stubs.UiStub;
import duke.tasks.Task;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseEvent_noDescriptionExceptionThrown_eventCommandOnly() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event";
        String expected = "(・´з`・) Uh oh... please add a description\n" + ui.showLine() + "\n";

        NoDescException e = assertThrows(NoDescException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noDescriptionExceptionThrown_whiteSpaceDesc() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event      ";
        String expected = "(・´з`・) Uh oh... please add a description\n" + ui.showLine() + "\n";

        NoDescException e = assertThrows(NoDescException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noDescriptionExceptionThrown_whiteSpaceFromToCorrect() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event     /from 2023-09-09 18:00 /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add a description\n" + ui.showLine() + "\n";

        NoDescException e = assertThrows(NoDescException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_invalidEventExceptionThrown_noDesc() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event/from 2023-09-09 19:00 /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... improper event format!\n" + ui.showLine() + "\n";

        InvalidEventException e = assertThrows(InvalidEventException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_invalidEventExceptionThrown_noFrom() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... improper event format!\n" + ui.showLine() + "\n";

        InvalidEventException e = assertThrows(InvalidEventException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noStartException_fromWithNoSpaces() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from/to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add a start date\n" + ui.showLine() + "\n";

        NoStartException e = assertThrows(NoStartException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noStartException_fromWithWhiteSpaces() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from     /to 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add a start date\n" + ui.showLine() + "\n";

        NoStartException e = assertThrows(NoStartException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noEndException_noTo() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00";
        String expected = "(・´з`・) Uh oh... please add an end date\n" + ui.showLine() + "\n";

        NoEndException e = assertThrows(NoEndException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noEndException_toWithNoSpace() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00 /to";
        String expected = "(・´з`・) Uh oh... please add an end date\n" + ui.showLine() + "\n";

        NoEndException e = assertThrows(NoEndException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_noEndException_toWithWhiteSpaces() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00 /to     ";
        String expected = "(・´з`・) Uh oh... please add an end date\n" + ui.showLine() + "\n";

        NoEndException e = assertThrows(NoEndException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_invalidStartEndException() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 19:00 /to 2023-09-09 14:00";
        String expected = "(・´з`・) Uh oh... start must be after end!\n" + ui.showLine() + "\n";

        InvalidStartEndException e = assertThrows(InvalidStartEndException.class,
                () -> parser.parseEvent(input));
        ui.showError(e.getMessage());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_wrongDateFormat_words() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from now /to tmr";
        String expected = "(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format\n" + ui.showLine() + "\n";

        try {
            parser.parseEvent(input);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_wrongDateFormat_invalidDates() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-13-21 /to 2023-13-90";
        String expected = "(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format\n" + ui.showLine() + "\n";

        try {
            parser.parseEvent(input);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseEvent_addToListSuccessfully() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt", ui);
        TaskListStub list = new TaskListStub(new ArrayList<>(), storage, ui);
        Parser parser = new Parser(storage, list, ui);

        String input = "event live lecture /from 2023-09-09 10:00 /to 2023-09-09 14:00";
        String expectedEvent = "[E] [ ] live lecture (from: 2023-09-09 10:00 to: 2023-09-09 14:00)" + "\n";
        String expectedMessage = "(｀･ω･´)ﾉ New task added:\n" + expectedEvent +
                "Now you have 1 task in the list!\n" + ui.showLine() + "\n";
        try {
            parser.parseEvent(input);
            assertEquals(expectedMessage, outContent.toString()); //check message to user
            ArrayList<Task> tasks = storage.loadTasks();
            assertEquals("1. " + expectedEvent, list.listTasks(tasks)); //check storage

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
