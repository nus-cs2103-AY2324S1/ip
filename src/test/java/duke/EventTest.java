package duke;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void toString_success() throws Exception {
        assertEquals("[E][ ] nus boxing(from: 03 April 2003 to: 04 May 2004)", new Event("nus boxing", LocalDate.of(2003, 04, 03), LocalDate.of(2004, 05, 04)).toString());
    }

    @Test
    public void changeFormat_success() {
        assertEquals(LocalDate.of(2003, 4, 3).toString(), "2003-04-03");
    }

}


