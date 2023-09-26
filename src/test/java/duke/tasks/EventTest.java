package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventTest {
    @Test
    public void getFrom_areBothDates() {
        //input matches output format
        assertEquals("23/02/2023", new Event("holidays", "23/02/2023", "03/24/2023").getFrom());

        //even if MM/dd/yyyy is used, it should still match
        assertEquals("23/02/2023", new Event("holidays", "02/23/2023", "03/24/2023").getFrom());

        //a different format from the inputs is not equal to the output
        assertNotEquals("23/02/2023", new Event("holidays", "2023/02/23", "03/24/2023").getFrom());

        //if to is not a date, the format is not changed
        assertEquals("23/02/2023", new Event("holidays", "23/02/2023", "someday").getFrom());
        assertNotEquals("23/02/2023", new Event("holidays", "02/23/2023", "whenever").getFrom());
    }

    @Test
    public void getFrom_notDates() {
        //if from is not a date at all it should not change
        assertEquals("midnight", new Event("Call with family", "midnight", "2 AM").getFrom());
    }

    @Test
    public void getTo_areBothDates() {
        //input matches output format
        assertEquals("24/03/2023", new Event("holidays", "23/02/2023", "24/03/2023").getTo());

        //even if MM/dd/yyyy is used, it should still match
        assertEquals("24/03/2023", new Event("holidays", "02/23/2023", "03/24/2023").getTo());

        //a different format from the inputs is not equal to the output
        assertNotEquals("24/03/2023", new Event("holidays", "2023/02/23", "03-24-2023").getTo());

        //if to is not a date, the format is not changed
        assertEquals("24/03/2023", new Event("holidays", "always", "24/03/2023").getTo());
        assertNotEquals("23/02/2023", new Event("holidays", "never", "02/23/2023").getTo());
    }

    @Test
    public void getTo_notDates() {
        //if to is not a date at all it should not change
        assertEquals("2 AM", new Event("Call with family", "midnight", "2 AM").getTo());
    }

}
