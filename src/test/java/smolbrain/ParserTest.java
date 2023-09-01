package smolbrain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import smolbrain.exception.InvalidDateTimeException;
import smolbrain.exception.InvalidNumberException;
import smolbrain.exception.InvalidRangeException;
import smolbrain.exception.MissingDescriptionException;
import smolbrain.exception.MissingTimeException;
import smolbrain.task.Task;

public class ParserTest {

    @Test
    public void parseTodoTest1() {
        String input = "todo todo1";
        String output = "[T][ ] todo1";
        try {
            Task task = Parser.parseLoading(input);
            assertEquals(output, task.toString());
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            fail();
        }
    }

    @Test
    public void parseTodoTest2() {
        String input = "todo tododescription 123";
        String output = "[T][ ] tododescription 123";
        try {
            Task task = Parser.parseLoading(input);
            assertEquals(output, task.toString());
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            fail();
        }
    }

    @Test
    public void parseInvalidTodo() {
        String input = "todo";
        String output = "☹ OOPS!!! The description of a todo cannot be empty.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseDeadlineTest1() {
        String input = "deadline newdeadline1 /by 06/12/2020 1800";
        String output = "[D][ ] newdeadline1 (by: Dec 06 2020 18:00)";
        try {
            Task task = Parser.parseLoading(input);
            assertEquals(output, task.toString());
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            fail();
        }
    }

    @Test
    public void parseDeadlineTest2() {
        String input = "deadline newdeadline1 with spaced out text /by 16/02/2120 1100";
        String output = "[D][ ] newdeadline1 with spaced out text (by: Feb 16 2120 11:00)";
        try {
            Task task = Parser.parseLoading(input);
            assertEquals(output, task.toString());
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            fail();
        }
    }

    @Test
    public void parseInvalidDeadlineTest1() {
        String input = "deadline";
        String output = "☹ OOPS!!! The description of a deadline cannot be empty.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidDeadlineTest2() {
        String input = "deadline description";
        String output = "☹ OOPS!!! The ending time of a deadline cannot be empty.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidDeadlineTest3() {
        String input = "deadline description /by 12/2/2000 1900";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidDeadlineTest4() {
        String input = "deadline description /by 12/31/2000 1900";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidDeadlineTest5() {
        String input = "deadline description /by 12/11/2000 2500";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidDeadlineTest6() {
        String input = "deadline description /by 12/11/2000 11:00";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseEventTest1() {
        String input = "event newevent1 /from 06/12/2020 1800 /to 06/12/3020 1800";
        String output = "[E][ ] newevent1 (from: Dec 06 2020 18:00 to: Dec 06 3020 18:00)";
        try {
            Task task = Parser.parseLoading(input);
            assertEquals(output, task.toString());
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            fail();
        }
    }

    @Test
    public void parseEventTest2() {
        String input = "event newevent2 with space text /from 06/11/1900 1800 /to 06/12/2020 2300";
        String output = "[E][ ] newevent2 with space text (from: Nov 06 1900 18:00 to: Dec 06 2020 23:00)";
        try {
            Task task = Parser.parseLoading(input);
            assertEquals(output, task.toString());
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            fail();
        }
    }

    @Test
    public void parseInvalidEventTest1() {
        String input = "event";
        String output = "☹ OOPS!!! The description of a event cannot be empty.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidEventTest2() {
        String input = "event eventdescr";
        String output = "☹ OOPS!!! The start time of a event cannot be empty.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidEventTest3() {
        String input = "event eventdescr /from 06/11/1900 1800";
        String output = "☹ OOPS!!! The end time of a event cannot be empty.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidEventTest4() {
        String input = "event description /from 12/2/2000 1900 /to 12/2/2000 1900";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidEventTest5() {
        String input = "event description /from 12/31/2000 1900 /to 12/31/2000 1900";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidEventTest6() {
        String input = "event description /from 12/11/2000 2500 /to 12/11/2000 2500";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }

    @Test
    public void parseInvalidEventTest7() {
        String input = "event description /from 12/11/2000 11:00 /to 12/11/2000 11:00";
        String output = "☹ OOPS!!! Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
        try {
            Task task = Parser.parseLoading(input);
            fail();
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException | InvalidDateTimeException
                 | MissingDescriptionException e) {
            assertEquals(output, new UiStub().showError(e));
        }
    }
}
