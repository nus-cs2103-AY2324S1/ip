package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void testDeadlines_initialization() {
        Deadlines deadline = new Deadlines("Submit Report", "2023/09/21");
        assertEquals("[D][ ] Submit Report (by: 09-21-2023)", deadline.toString());
    }

    @Test
    public void testDeadlines_withStatusAndNotes() {
        Deadlines deadline = new Deadlines("Submit Report", "2023/09/21", true, "Important");
        assertEquals("[D][X] Submit Report (by: 09-21-2023)", deadline.toString());
        assertEquals("Important", deadline.getNotes());
    }

    @Test
    public void testGetSavingFormat() {
        Deadlines deadline = new Deadlines("Submit Report", "2023/09/21");
        assertEquals("[D] | [ ] | Submit Report | 2023/09/21 | ", deadline.getSavingFormat());
    }

    @Test
    public void testToTask_fromSavedData() {
        String[] savedData = {"[D]", "[X]", "Submit Report", "2023/09/21", "Important"};
        Deadlines deadline = Deadlines.toTask(savedData);
        assertEquals("[D][X] Submit Report (by: 09-21-2023)", deadline.toString());
        assertEquals("Important", deadline.getNotes());
    }
}
