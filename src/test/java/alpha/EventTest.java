package alpha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void makeEvent_invalidDate_dateTimeParse_exceptionThrown() {
        try {
            Event.makeEvent("meeting", "2022-15-01", "2022-20-01");
        } catch (Exception e) {
            assertEquals("Text '2022-15-01' could not be parsed: " +
                    "Invalid value for MonthOfYear (valid values 1 - 12): 15", e.getMessage());
        }
    }

    @Test
    public void makeEvent_invalidTime_dateTimeParse_exceptionThrown() {
        try {
            Event.makeEvent("meeting", "2022-01-01 2500", "2022-01-01");
        } catch (Exception e) {
            assertEquals("Text '2500' could not be parsed: Invalid " +
                    "value for HourOfDay (valid values 0 - 23): 25", e.getMessage());
        }
    }

    @Test
    public void makeEvent_missingDescription_exceptionThrown() {
        try {
            Event.makeEvent("     ", "2022-01-01", "2022-01-01");
        } catch (Exception e) {
            assertEquals("Missing a description!", e.getMessage());
        }
    }
}
