import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import duke.Date;

public class DateTest {

    @Test
    void testDate1() {
        Date date = new Date("2000-09-09", null);
        assertEquals(date.toString(), "Sep 9 2000");
    }

    @Test
    void testDate2() {
        Date date = new Date("2000-09-09",  "12:00");
        assertEquals(date.toString(), "Sep 9 2000 12:00 PM");
    }

    @Test
    void testDate3() {
        Date date = new Date(null,  "16:30");
        assertEquals(date.toString(), "04:30 PM");
    }
}
