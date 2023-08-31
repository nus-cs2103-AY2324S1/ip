package oscar.command;

import org.junit.jupiter.api.Test;
import oscar.exception.OscarException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventCommandTest {
    @Test
    public void validate_validEventCommand_success() throws OscarException {
        EventCommand eventCommand = new EventCommand("Meeting /from 2023-07-01 1400 /to 2023-07-01 1600");
        String[] validatedDetails = eventCommand.validate();
        assertEquals("Meeting", validatedDetails[0]);
        assertEquals("2023-07-01 1400", validatedDetails[1]);
        assertEquals("2023-07-01 1600", validatedDetails[2]);
    }

    @Test
    public void validate_missingSlash_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("Meeting from 2023-07-01 1400 to 2023-07-01 1600");
            eventCommand.validate();
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! The event task is not formatted correctly.\n" +
                    "Please use the format: 'event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.\n",
                    e.getMessage());
        }
    }

    @Test
    public void validate_emptyDescription_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand(" /from 2023-07-01 1400 /to 2023-07-01 1600");
            eventCommand.validate();
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! The description of an event task cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void validate_emptyStart_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("Meeting /from  /to 2023-07-01 1600");
            eventCommand.validate();
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! The start date and time of an event task cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void validate_emptyEnd_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("Meeting /from 2023-07-01 1400 /to ");
            eventCommand.validate();
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! The event task is not formatted correctly.\n" +
                            "Please use the format: 'event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.\n",
                    e.getMessage());
        }
    }

    @Test
    public void validate_invalidTime_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("Meeting /from 2023-07-01 2500 /to 2023-07-01 1600");
            eventCommand.validate();
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! Please enter a valid date and time in the format 'yyyy-MM-dd HHmm'.\n", e.getMessage());
        }
    }

    @Test
    public void validate_endBeforeStart_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("Meeting /from 2023-07-01 1700 /to 2023-07-01 1600");
            eventCommand.validate();
            fail();
        } catch (OscarException e) {
            assertEquals("Sorry! End date and time must be after start date and time.\n", e.getMessage());
        }
    }
}
