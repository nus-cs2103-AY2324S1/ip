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
        Integer[] position = new Integer[1];
        String input = "todo finish homework";
        String[] expectedOutput = {"todo", "finish homework"};
        assertArrayEquals(expectedOutput, parser.parseToRemoveUnknownCommands(position, input));
    }

    @Test
    public void parseForDeadline_correctlyFormattedInput_generateExpectedOutput() throws NoByException {
        Parser parser = new Parser();
        String taskDetails = "finish homework /by 2023-09-01 1500";
        String[] expectedOutput = {"finish homework", "2023-09-01 1500"};
        assertArrayEquals(expectedOutput, parser.parseForDeadline(taskDetails));
    }

    @Test
    public void parseForEvent_correctlyFormattedInput_generateExpectedOutput() throws NoToException, NoFromException {
        Parser parser = new Parser();
        String taskDetails = "attend meeting /from 2023-09-01 1500 /to 2023-09-01 1600";
        String[] expectedOutput = {"attend meeting", "2023-09-01 1500", "2023-09-01 1600"};
        assertArrayEquals(expectedOutput, parser.parseForEvent(taskDetails));
    }

    @Test
    public void parseForDate_correctlyFormattedInput_generateExpectedOutput() throws InvalidDateException, WrongDateFormatException{
        Parser parser = new Parser();
        String input = "2023-09-01 1500";
        String[] expectedOutput = {"2023-09-01", "15", "00"};
        assertArrayEquals(expectedOutput, parser.parseForDate(input));
    }

    @Test
    public void parseToRemoveUnknownCommands_invalidInput_exceptionThrown() {
        Parser parser = new Parser();
        Integer[] position = new Integer[1];
        String input = "invalid";
        try {
            parser.parseToRemoveUnknownCommands(position, input);
            fail("Expected UnknownCommandException to be thrown");
        } catch (UnknownCommandException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDeadline_noByInInput_exceptionThrown() {
        Parser parser = new Parser();
        String taskDetails = "finish homework tomorrow";
        try {
            parser.parseForDeadline(taskDetails);
            fail("Expected NoByException to be thrown");
        } catch (NoByException e) {
            // Test passed
        }
    }

    @Test
    public void parseForEvent_noFromInInput_exceptionThrown() throws NoToException {
        Parser parser = new Parser();
        String taskDetails = "attend meeting 9am to 11am";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected NoFromException to be thrown");
        } catch (NoFromException e) {
            // Test passed
        }
    }

    @Test
    public void parseForEvent_noToInInput_exceptionThrown() throws NoFromException {
        Parser parser = new Parser();
        String taskDetails = "attend meeting /from 9am 11am";
        try {
            parser.parseForEvent(taskDetails);
            fail("Expected NoToException to be thrown");
        } catch (NoToException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDate_wrongFormatForDate_exceptionThrown() throws InvalidDateException {
        Parser parser = new Parser();
        String input = "2023/09/01 1500";
        try {
            parser.parseForDate(input);
            fail("Expected WrongDateFormatException to be thrown");
        } catch (WrongDateFormatException e) {
            // Test passed
        }
    }

    @Test
    public void parseForDate_invalidDateInput_exceptionThrown() throws WrongDateFormatException {
        Parser parser = new Parser();
        String input = "2023-13-01 1500";
        try {
            parser.parseForDate(input);
            fail("Expected InvalidDateException to be thrown");
        } catch (InvalidDateException e) {
            // Test passed
        }
    }
}
