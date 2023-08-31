package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomDateTest {
    @Test
    public void strToDateTime_checkDate_success() {
        assertEquals("2019-04-04T02:00",
                new CustomDate().strToDateTime("4/4/2019 0200").toString());
    }

}
