package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Event Tester is used to test the Event class
 */
public class EventTester {

    /**
     * Testing multiple random valid inputs for taskValidator
     * should pass if no exception is thrown
     */
    @Test
    public void taskValidator_validInputs_success() throws WrongInputException {
        // We assume that it has passed the command test successfully
        // That is it starts with any command verbs

        // To verify that taskValidator has successfully worked, it should not throw any exception

        // Test case 1
        String input1 = "event test /from 25/08/2023 1800 /to 25/08/2023 1900";
        assertDoesNotThrow(() -> Event.taskValidator(input1));

        // Test case 2
        String input2 = "event event /from 13/01/2021 0000 /to 13/01/2021 0000";
        assertDoesNotThrow(() -> Event.taskValidator(input2));

        // Test case 3
        String input3 = "event 'hey' /from 31/12/2022 2359 /to 1/01/2023 0000";
        assertDoesNotThrow(() -> Event.taskValidator(input3));
    }

    /**
     * Testing additional commands for taskValidator
     * should pass if no exception is thrown and the additional commands are ignored
     */
    @Test
    public void taskValidator_additionalCommands_success() {
        // Test case 4
        String input4 = "event hey /from 25/08/2023 1800 /to 25/08/2023 1700 /from 25/08/2023 1800";
        try {
            Event.taskValidator(input4);
        } catch (WrongInputException e) {
            Assertions.fail();
        }
    }

    /**
     * Testing missing /to commands for taskValidator
     */
    @Test
    public void taskValidator_missingTo_exceptionThrown() {
        // Test case 1
        String input1 = "event test /from 25/08/2023 1800";
        try {
            Event.taskValidator(input1);
            Assertions.fail();
        } catch (WrongInputException e) {
            assertEquals("Invalid Format: /to command is required\n"
                + "Recommendation: Use the /to command in 'event <event_name> /from <start> /to <end>'",
                    e.getMessage());
        }

    }

    /**
     * Testing missing /from commands for taskValidator
     */
    @Test
    public void taskValidator_missingFrom_exceptionThrown() {
        // Test case 2
        String input2 = "event test /to 25/08/2023 1900";
        try {
            Event.taskValidator(input2);
            Assertions.fail();
        } catch (WrongInputException e) {
            assertEquals("Invalid Format: /from command is required\n"
                    + "Recommendation: Use the /from command in 'event <event_name> /from <start> /to <end>'",
                    e.getMessage());
        }
    }

    /**
     * Testing missing task name for taskValidator
     */
    @Test
    public void taskValidator_missingTaskName_exceptionThrown() {
        // Test case 3
        String input3 = "event /from 25/08/2023 1800 /to 25/08/2023 1900";
        try {
            Event.taskValidator(input3);
            Assertions.fail();
        } catch (WrongInputException e) {
            assertEquals("Invalid Format: Task name cannot be blank\n"
                    + "Recommendation: Enter a non-blank name", e.getMessage());
        }
    }

    /**
     * Testing wrong format for taskValidator
     * Includes all the test cases above for wrong format
     */
    @Test
    public void taskValidator_wrongFormat_exceptionThrown() {
        taskValidator_missingTaskName_exceptionThrown();;
        taskValidator_missingFrom_exceptionThrown();
        taskValidator_missingTo_exceptionThrown();
    }

    /**
     * Testing all valid inputs for taskValidator
     * Includes all the test cases above for valid inputs
     */
    @Test
    public void taskValidator_allValidInputs_success() {
        try {
            taskValidator_validInputs_success();
            taskValidator_additionalCommands_success();
        } catch (WrongInputException e) {
            Assertions.fail();
        }
    }

    /**
     * Testing out the convertToSaveFormat method by seeing if the output is of the format
     * E | isDone | task_name | start_time | end_time where time is in the format
     * MMM-dd-yyyy hhmm a
     */
    @Test
    public void testConvertToSaveFormat() {
        try {
            Event event = new Event("test", DateTimeStub.createDateTime("25/08/2023 1800"),
                    DateTimeStub.createDateTime("25/08/2023 1900"));
            assertEquals("E | false | test | Aug-25-2023 1900 PM | Aug-25-2023 1800 PM", event.convertToSaveFormat());

            event.quietlyCompleteTask();
            assertEquals("E | true | test | Aug-25-2023 1900 PM | Aug-25-2023 1800 PM", event.convertToSaveFormat());
        } catch (WrongInputException e) {
            Assertions.fail();
        }

    }

}
