package ax.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ListItemTest {

    @Test
    void getText() {
        ListItem temp = new ListItem("yo");
        assertEquals(temp.getText(), "yo");
    }

    @Test
    void getDone() {
        ListItem temp = new ListItem("yo");
        assertFalse(temp.getDone());
        temp.setDone(true);
        assertTrue(temp.getDone());
    }

    @Test
    void toggleDone() {
        ListItem temp = new ListItem("yo");
        temp.toggleDone();
        assertTrue(temp.getDone());
        temp.toggleDone();
        assertFalse(temp.getDone());
    }

    @Test
    void setDone() {
        ListItem temp = new ListItem("yo");
        assertFalse(temp.getDone());
        temp.setDone(true);
        assertTrue(temp.getDone());
        temp.setDone(false);
        assertFalse(temp.getDone());
    }

    @Test
    void parseDate() {
        ListItem temp = new ListItem("yo");
        LocalDate date = temp.parseDate("2023-01-01");
        assertEquals(date.getYear(), 2023);
        assertEquals(date.getMonthValue(), 1);
        assertEquals(date.getDayOfMonth(),1);
    }

    @Test
    void testToString() {
        ListItem temp = new ListItem("yo");
        assertEquals(temp.toString(), "[ ] yo");
        temp.setDone(true);
        assertEquals(temp.toString(), "[✅] yo");
    }
}