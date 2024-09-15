package emiya.parser;

import emiya.emiyaexception.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    // featureUnderTest_testScenario_expectedBehavior

    @Test
    public void parseToRemoveUnknownCommands_correctlyFormattedInput_generateExpectedOutput() throws UnknownCommandException {
        Parser parser = new Parser();
        String input = "todo finish homework";
        String[] expectedOutput = {"todo", "finish homework"};
        assertArrayEquals(expectedOutput, parser.parseToRemoveUnknownCommands(input));
    }

    @Test
    public void parseForDeadline_correctlyFormattedInput_generateExpectedOutput() throws NoByException,
            UnknownCommandException {
        Parser parser = new Parser();
        String taskDetails = "finish homework /by 2023-09-01 1500";
        String[] expectedOutput = {"finish homework", "2023-09-01 1500"};
        assertArrayEquals(expectedOutput, parser.parseForDeadline(taskDetails));
    }

    @Test
    public void parseForEvent_correctlyFormattedInput_generateExpectedOutput() throws NoToException, NoFromException,
            UnknownCommandException {
        Parser parser = new Parser();
        String taskDetails = "attend meeting /from 2023-09-01 1500 /to 2023-09-01 1600";
        String[] expectedOutput = {"attend meeting", "2023-09-01 1500", "2023-09-01 1600"};
        assertArrayEquals(expectedOutput, parser.parseForEvent(taskDetails));
    }

    @Test
    public void parseForDate_correctlyFormattedInput_generateExpectedOutput() throws InvalidDateTimeException,
            WrongDateTimeFormatException {
        Parser parser = new Parser();
        String input = "2023-09-01 1500";
        String[] expectedOutput = {"2023-09-01", "15", "00"};
        assertArrayEquals(expectedOutput, parser.parseForDate(input));
    }

    @Test
    public void parseToRemoveUnknownCommands_invalidInput1_exceptionThrown() {
        Parser parser = new Parser();
        String input = "invalid";
        try {
            parser.parseToRemoveUnknownCommands(input);
            fail("Expected UnknownCommandException to be thrown");
        } catch (UnknownCommandException e) {
            // Test passed
        }
    }

    @Test
    public void parseToRemoveUnknownCommands_invalidInput2_exceptionThrown() {
        Parser parser = new Parser();
        String input = "dead ";
        try {
            parser.parseToRemoveUnknownCommands(input);
            fail("Expected UnknownCommandException to be thrown");
        } catch (UnknownCommandException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDeadline_noByInInput1_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "finish homework tomorrow";
        try {
            parser.parseForDeadline(taskDetails);
            fail("Expected NoByException to be thrown");
        } catch (UnknownCommandException e){
            fail("Did not throw correct exception");
        } catch (NoByException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDeadline_noByInInput2_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "finish homework by tomorrow";
        try {
            parser.parseForDeadline(taskDetails);
            fail("Expected NoByException to be thrown");
        } catch (UnknownCommandException e){
            fail("Did not throw correct exception");
        } catch (NoByException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDeadline_noDeadlineDescription_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "/by 2023-09-09 1800";
        try {
            parser.parseForDeadline(taskDetails);
            fail("Expected UnknownCommandException to be thrown");
        } catch (NoByException e){
            fail("Did not throw correct exception");
        } catch (UnknownCommandException e) {
            // Test passed
        }
    }

    @Test
    public void parseForEvent_noFromInInput1_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "attend meeting 9am to 11am";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected NoFromException to be thrown");
        } catch (NoToException | UnknownCommandException e) {
            fail("Wrong Exception thrown");
        } catch (NoFromException e) {
            // Test passed
        }
    }

    @Test
    public void parseForEvent_noFromInInput2_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "attend meeting from 9am /to 11am";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected NoFromException to be thrown");
        } catch (NoToException | UnknownCommandException e) {
            fail("Wrong Exception thrown");
        } catch (NoFromException e) {
            // Test passed
        }
    }


    @Test
    public void parseForEvent_noToInInput1_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "attend meeting /from 9am 11am";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected NoToException to be thrown");
        } catch (NoFromException | UnknownCommandException e) {
            fail("Wrong Exception thrown");
        } catch (NoToException e) {
            // Test passed
        }
    }

    @Test
    public void parseForEvent_noToInInput2_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "attend meeting /from 9am to 11am";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected NoToException to be thrown");
        } catch (NoFromException | UnknownCommandException e) {
            fail("Wrong Exception thrown");
        } catch (NoToException e) {
            // Test passed
        }
    }

    @Test
    public void parseForEvent_noEventDescription_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "/from 2023-09-09 1800 /to 2023-09-09 2000";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected UnknownCommandException to be thrown");
        } catch (NoFromException | NoToException e){
            fail("Did not throw correct exception");
        } catch (UnknownCommandException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDate_wrongFormatForDate1_exceptionThrown() {
        Parser parser = new Parser();
        String input = "2023/09/01 1500";
        try {
            parser.parseForDate(input);
            fail("Expected WrongDateTimeFormatException to be thrown");
        } catch (InvalidDateTimeException e) {
            fail("Wrong exception thrown");
        } catch (WrongDateTimeFormatException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDate_wrongFormatForDate2_exceptionThrown() {
        Parser parser = new Parser();
        String input = "1/2/2023 1500";
        try {
            parser.parseForDate(input);
            fail("Expected WrongDateTimeFormatException to be thrown");
        } catch (InvalidDateTimeException e) {
            fail("Wrong exception thrown");
        } catch (WrongDateTimeFormatException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDate_invalidDateInput1_exceptionThrown() {
        Parser parser = new Parser();
        String input = "2023-13-01 1500";
        try {
            parser.parseForDate(input);
            fail("Expected InvalidDateException to be thrown");
        } catch (WrongDateTimeFormatException e) {
            fail("Wrong exception thrown");
        } catch (InvalidDateTimeException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDate_invalidTimeInput1_exceptionThrown() {
        Parser parser = new Parser();
        String input = "2023-12-01 2569";
        try {
            parser.parseForDate(input);
            fail("Expected InvalidDateException to be thrown");
        } catch (WrongDateTimeFormatException e) {
            fail("Wrong exception thrown");
        } catch (InvalidDateTimeException e) {
            // Test passed
        }
    }
}
