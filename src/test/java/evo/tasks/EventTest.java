package evo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import evo.exceptions.InvalidDateAndTimeInputException;

/**
 * This class contains JUnit test cases to verify the functionality of the Event class.
 * It tests various scenarios for creating and formatting Event tasks, as well as handling
 * exceptions for invalid date and time inputs.
 *
 * @author NgChunMan
 */
public class EventTest {
    /**
     * Test case to ensure that the output message is correctly formatted for a valid input.
     * It creates an Event task with a valid date and time format and checks if the output message
     * matches the expected format.
     *
     * @throws InvalidDateAndTimeInputException If an invalid date and time input is encountered.
     */
    @Test
    public void outputMsg_validInput_success() throws InvalidDateAndTimeInputException {
        Event event = new Event("meeting", "from: 2019-12-13 1800 to: 2000");
        assertEquals("E | 0 | meeting | from: Dec 13 2019 6PM to: 8PM", event.outputMsg());
    }

    /**
     * Test case to ensure that the output message is correctly formatted for a valid input
     * read from a text file. It creates an Event task with a valid date and time format and
     * checks if the output message matches the expected format.
     *
     * @throws InvalidDateAndTimeInputException If an invalid date and time input is encountered.
     */
    @Test
    public void outputMsg_formatInputReadFromTextFile_success() throws InvalidDateAndTimeInputException {
        Event event = new Event("meeting", "from: Dec 13 2019 6PM to: 8PM");
        assertEquals("E | 0 | meeting | from: Dec 13 2019 6PM to: 8PM", event.outputMsg());
    }

    /**
     * Test case to verify that an exception is thrown when the 'to' part is missing in the input.
     * It creates an Event task with an invalid input missing the 'to' part and checks if an
     * InvalidDateAndTimeInputException is thrown with the expected error message.
     */
    @Test
    public void outputMsg_invalidDateMissingTo_exceptionThrown() {
        Event event = new Event("meeting", "from: 2019-02-11 1800 ");
        try {
            event.outputMsg();
            fail();
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            assertEquals("Please type in a valid date/time input. Eg: event project meeting /from " +
                    "2019-12-15 1800 /to 2000\n", invalidDateAndTimeInputException.getMessage());
        }
    }

    /**
     * Test case to verify that an exception is thrown when the 'from' part is missing in the input.
     * It creates an Event task with an invalid input missing the 'from' part and checks if an
     * InvalidDateAndTimeInputException is thrown with the expected error message.
     */
    @Test
    public void outputMsg_invalidDateMissingFrom_exceptionThrown() {
        Event event = new Event("meeting", "to: 1800 ");
        try {
            event.outputMsg();
            fail();
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            assertEquals("Please type in a valid date/time input. Eg: event project meeting /from " +
                    "2019-12-15 1800 /to 2000\n", invalidDateAndTimeInputException.getMessage());
        }
    }

    /**
     * Test case to ensure that the string representation of the Event task is correctly formatted
     * for a valid input. It creates an Event task with a valid date and time format and checks if
     * the string representation matches the expected format.
     */
    @Test
    public void toString_validInput_success() {
        Event event = new Event("meeting", "from: 2019-12-13 1800 to: 2000");
        assertEquals("[E][ ] meeting (from: Dec 13 2019 6PM to: 8PM)", event.toString());
    }

    /**
     * Test case to ensure that the string representation of the Event task is correctly formatted
     * for a valid input read from a text file. It creates an Event task with a valid date and time
     * format and checks if the string representation matches the expected format.
     */
    @Test
    public void toString_formatInputReadFromTextFile_success() {
        Event event = new Event("meeting", "from: Dec 13 2019 6PM to: 8PM");
        assertEquals("[E][ ] meeting (from: Dec 13 2019 6PM to: 8PM)", event.toString());
    }
}
