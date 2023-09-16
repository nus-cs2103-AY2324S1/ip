package duke.filemanagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeDetectorTest {
    @Test
    public void testDateFormat(){
        DateTimeDetector detector = new DateTimeDetector();
        String date = "15/12/2023";
        String formattedDate = detector.format((date));
        assertEquals(formattedDate, "Dec 15 2023");
    }

    @Test
    public void testDateTimeFormat(){
        DateTimeDetector detector = new DateTimeDetector();
        String dateTime = "15/12/2023 1800";
        String formattedDate = detector.format((dateTime));
        assertEquals(formattedDate, "Dec 15 2023 1800");
    }
}
