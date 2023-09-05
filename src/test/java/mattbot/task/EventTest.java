package mattbot.task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class EventTest {

    private LocalDateTime lDT1 = LocalDateTime.of(2023, 9, 13, 22, 34);

    @Test
    public void eventCreation_normalTime_success() throws Exception {
        assertEquals(new Event("Test Event", false, lDT1, lDT1).identifier(), "E");
    }

    @Test public void eventCreation_invalidTime_exceptionThrown() throws Exception {
        // Throws DateTimeParseException
        LocalDateTime lDT1 = LocalDateTime.of(2023, 13, 13, 22, 34);
    }

}
