package duke;

import org.junit.jupiter.api.Test;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_byeString_byeCommand() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c instanceof ExitCommand);
        } catch (RichieException e) {
            fail("Exception thrown by bye command");
        }
    }

    @Test
    void parse_listString_listCommand() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (RichieException e) {
            fail("Exception thrown by list command");
        }
    }

    @Test
    void parse_markEmptyIndexString_exception() {
        try {
            Command c = Parser.parse("mark");
            fail("Exception should be thrown by mark command with empty index");
        } catch (RichieException e) {
            assertEquals("Incomplete input, please specify which task to mark", e.getMessage());
        }
    }

    @Test
    void parse_markCharString_exception() {
        try {
            Command c = Parser.parse("mark hello");
            fail("Exception should be thrown by mark command followed by non-int");
        } catch (RichieException e) {
            assertEquals("Invalid input, please enter a number", e.getMessage());
        }

        try {
            Command b = Parser.parse("mark %^$%");
            fail("Exception should be thrown by mark command followed by non-int");
        } catch (RichieException e) {
            assertEquals("Invalid input, please enter a number", e.getMessage());
        }
    }

    @Test
    void parse_deleteEmptyIndexString_exception() {
        try {
            Command c = Parser.parse("delete");
            fail("Exception should be thrown by delete command with empty index");
        } catch (RichieException e) {
            assertEquals("Incomplete input, please specify which task to delete", e.getMessage());
        }
    }

    @Test
    void parse_deleteCharString_exception() {
        try {
            Command a = Parser.parse("delete -$%^");
            fail("Exception should be thrown by delete command followed by non-int");
        } catch (RichieException e) {
            assertEquals("Invalid input, please enter a number", e.getMessage());
        }

        try {
            Command c = Parser.parse("delete hello");
            fail("Exception should be thrown by delete command followed by non-int");
        } catch (RichieException e) {
            assertEquals("Invalid input, please enter a number", e.getMessage());
        }
    }

    @Test
    void deadline_missingBy_Exception() {
        try {
            Command c = Parser.parse("deadline ");
            fail("Exception should be thrown by deadline command without '/by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please enter '/by' followed by a date and " +
                    "time that the task should be done by", e.getMessage());
        }

        try {
            Command a = Parser.parse("deadline homework");
            fail("Exception should be thrown by deadline command without '/by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please enter '/by' followed by a date and " +
                    "time that the task should be done by", e.getMessage());
        }

        try {
            Command b = Parser.parse("deadline homework by 2/12/2002 0400");
            fail("Exception should be thrown by deadline command without '/by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please enter '/by' followed by a date and " +
                    "time that the task should be done by", e.getMessage());
        }
    }

    @Test
    void deadline_missingDescriptionOrDeadline_Exception() {
        try {
            Command c = Parser.parse("deadline");
            fail("Exception should be thrown by deadline command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! Either the description or the deadline is empty!", e.getMessage());
        }

        try {
            Command d = Parser.parse("deadline /by 2/12/2002 0400");
            fail("Exception should be thrown by deadline command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! Either the description or the deadline is empty!", e.getMessage());
        }
    }

    @Test
    void deadline_noSpacesAroundBy_Exception() {
        try {
            Command c = Parser.parse("deadline homework/by2/12/2002 0400");
            fail("Exception should be thrown by deadline command without spaces around '/by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/by'", e.getMessage());
        }

        try {
            Command a = Parser.parse("deadline homework/by 2/12/2002 0400");
            fail("Exception should be thrown by deadline command without spaces around '/by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/by'", e.getMessage());
        }

        try {
            Command b = Parser.parse("deadline homework /by2/12/2002 0400");
            fail("Exception should be thrown by deadline command without spaces around '/by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/by'", e.getMessage());
        }

        try {
            Command b = Parser.parse("deadline homework /by");
            fail("Exception should be thrown by deadline command without spaces around 'by'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/by'", e.getMessage());
        }
    }

    @Test
    void deadline_wrongDateTimeFormat_Exception() {
        try {
            Command c = Parser.parse("deadline homework /by 2/13/2002 0400");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command a = Parser.parse("deadline homework /by 32/12/2002 0400");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command b = Parser.parse("deadline homework /by 2/12/2002 2500");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command d = Parser.parse("deadline homework /by 2/12/2002");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command a = Parser.parse("deadline homework /by ");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }
    }

    @Test
    void deadline_validInput_DeadlineCommand() {
        try {
            Command c = Parser.parse("deadline homework /by 2/12/2002 0900");
            assertTrue(c instanceof DeadlineCommand);
        } catch (RichieException e) {
            fail("Exception should not be thrown by valid deadline command");
        }
    }

    @Test
    void deadline_noSpaceAfterCommandWord_Exception() {
        try {
            Command c = Parser.parse("deadlinehomework");
            fail("Exception should be thrown for no space after deadline command");
        } catch (RichieException e) {
            assertEquals("No command detected, please ensure that you leave a space after command word"
                    , e.getMessage());
        }
    }

    @Test
    void todo_missingDescription_Exception() {
        try {
            Command c = Parser.parse("todo");
            fail("Exception should be thrown by todo command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! The todo description is empty!", e.getMessage());
        }

        try {
            Command b = Parser.parse("todo ");
            fail("Exception should be thrown by todo command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! The todo description is empty!", e.getMessage());
        }
    }

    @Test
    void todo_validInput_todoCommand() {
        try {
            Command c = Parser.parse("todo do homework");
            assertTrue(c instanceof TodoCommand);
        } catch (RichieException e) {
            fail("Exception should not be thrown by valid todo command");
        }
    }

    @Test
    void event_missingFrom_Exception() {
        try {
            Command a = Parser.parse("event project meeting 2/12/2002 0400 /to 2/12/2002 0500");
            fail("Exception should be thrown by event command without '/from'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please enter '/from' followed by a date and " +
                    "time that the task should start from", e.getMessage());
        }

        try {
            Command b = Parser.parse("event project meeting ");
            fail("Exception should be thrown by event command without '/from'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please enter '/from' followed by a date and " +
                    "time that the task should start from", e.getMessage());
        }
    }

    @Test
    void event_missingTo_Exception() {
        try {
            Command a = Parser.parse("event project meeting /from 2/12/2002 0400 2/12/2002 0500");
            fail("Exception should be thrown by event command without '/to'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please enter '/to' followed by a date and " +
                    "time that the task should end", e.getMessage());
        }
    }

    @Test
    void event_missingDescriptionOrDeadline_Exception() {
        try {
            Command c = Parser.parse("event");
            fail("Exception should be thrown by event command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! The description of a event or the duration of the event is incomplete", e.getMessage());
        }

        try {
            Command a = Parser.parse("event /from 2/12/2002 0400 /to 2/12/2002 0500");
            fail("Exception should be thrown by event command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! The description of a event or the duration of the event is incomplete", e.getMessage());
        }

        try {
            Command b = Parser.parse("event homework /from /to ");
            fail("Exception should be thrown by event command without any description");
        } catch (RichieException e) {
            assertEquals("OOPS!! The description of a event or the duration of the event is incomplete", e.getMessage());
        }
    }

    @Test
    void deadline_noSpacesAroundFromOrTo_Exception() {
        try {
            Command c = Parser.parse("event homework /from2/12/2002 0400 /to 2/12/2002 0500");
            fail("Exception should be thrown by event command without spaces around '/from' and '/to'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/from' and '/to'", e.getMessage());
        }

        try {
            Command a = Parser.parse("event homework/from 2/12/2002 0400 /to 2/12/2002 0500");
            fail("Exception should be thrown by event command without spaces around '/from' and '/to'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/from' and '/to'", e.getMessage());
        }

        try {
            Command b = Parser.parse("event homework /from 2/12/2002 0400/to 2/12/2002 0500");
            fail("Exception should be thrown by event command without spaces around '/from' and '/to'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/from' and '/to'", e.getMessage());
        }

        try {
            Command d = Parser.parse("event homework /from 2/12/2002 0400 /to2/12/2002 0500");
            fail("Exception should be thrown by event command without spaces around '/from' and '/to'");
        } catch (RichieException e) {
            assertEquals("OOPS!! please ensure that there are spaces before and after '/from' and '/to'", e.getMessage());
        }
    }

    @Test
    void event_wrongDateTimeFormat_Exception() {
        try {
            Command c = Parser.parse("event homework /from 2/13/2002 0400 /to 2/12/2002 0400");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command a = Parser.parse("event homework /from 32/12/2002 0400 /to 2/13/2002 0500");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command b = Parser.parse("event homework /from 2/12/200 2400 /to 2/12/2003 0500");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }

        try {
            Command d = Parser.parse("event homework /from 2/12/2005 2700 /to 2/12/2005 2900");
            fail("Exception should be thrown by deadline command with invalid Date and Time");
        } catch (RichieException e) {
            assertEquals("Date and Time entered is in the wrong format!", e.getMessage());
        }
    }

    @Test
    void event_validInput_EventCommand() {
        try {
            Command c = Parser.parse("event homework /from 2/12/2002 0900 /to 2/12/2002 1000");
            assertTrue(c instanceof EventCommand);
        } catch (RichieException e) {
            fail("Exception should not be thrown by valid event command");
        }
    }

    @Test
    void event_noSpaceAfterCommandWord_Exception() {
        try {
            Command c = Parser.parse("eventhomework");
            fail("Exception should be thrown for no space after event command");
        } catch (RichieException e) {
            assertEquals("No command detected, please ensure that you leave a space after command word"
                    , e.getMessage());
        }
    }

    @Test
    void parse_noCommandWord_Exception() {
        try {
            Command c = Parser.parse("ajfksjfkdsjf");
            fail("Exception should be thrown when no command word is detected");
        } catch (RichieException e) {
            assertEquals("No command detected, please ensure that you leave a space after command word"
                    , e.getMessage());
        }
    }



}