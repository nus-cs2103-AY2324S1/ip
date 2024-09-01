package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {

    @Test
    public void verifyDatesInputEventCreation_fromBeforeToAfter_validDate() {
        assertDoesNotThrow(() -> {
            new Event("event1", "12/4/2022 1500", "12/6/2022 1500");
        });
    }

    @Test
    public void verifyDatesInputEventCreation_fromBeforeToAfterSameDay_validDate() {
        assertDoesNotThrow(() -> new Event("event1", "12/2/2022 1500", "12/2/2022 1600"));
    }

    @Test
    public void verifyDatesInputEventCreation_fromAfterToBeforeSameDay_invalidDate() {
        assertThrows(Exception.class, () -> new Event("event1", "12/2/2022 1600", "12/2/2022 1300"));
    }

    @Test
    public void verifyDatesInputEventCreation_fromToEqual_invalidDate() {
        assertThrows(Exception.class, () -> new Event("event1", "12/2/2022 1300", "12/2/2022 1300"));
    }

    @Test
    public void verifyDatesInputEventCreation_fromAfterToBefore_invalidDate() {
        assertThrows(Exception.class, () -> new Event("event1", "12/6/2022 1500", "12/2/2022 1500"));
    }

    @Test
    public void verifyDatesInputEventCreation_nonDateFormat_validDate() {
        assertDoesNotThrow(() -> new Event("event1", "Mon 12dec", "Tuesday 11nov"));
    }

    @Test
    public void verifyDatesInputEventCreation_singleNonDateFormat_validDate() {
        assertDoesNotThrow(() -> new Event("event1", "Mon 12dec", "12/2/2022 1500"));
    }
}
