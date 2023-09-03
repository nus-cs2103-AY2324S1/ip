package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testFormatDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime date1 = LocalDateTime.parse("01/11/2023 20:00", dateTimeFormatter);
        assertEquals("Nov 1 2023 20:00", Deadline.formatDateTime(date1));

        LocalDateTime date2 = LocalDateTime.parse("10/01/2023 01:00", dateTimeFormatter);
        assertEquals("Jan 10 2023 01:00", Deadline.formatDateTime(date2));
    }

    @Test
    public void testTodoStringConversion() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dueDate = LocalDateTime.parse("10/10/2023 10:10", dateTimeFormatter);

        Deadline test = new Deadline("Deadline Test", dueDate);
        assertEquals("[D] [ ] Deadline Test (by: Oct 10 2023 10:10)", test.toString());

        test.markAsDone();
        assertEquals("[D] [X] Deadline Test (by: Oct 10 2023 10:10)", test.toString());

        test.markAsUndone();
        assertEquals("[D] [ ] Deadline Test (by: Oct 10 2023 10:10)", test.toString());
    }

    @Test
    public void testTodoSavedStringConversion() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dueDate = LocalDateTime.parse("11/11/2023 11:11", dateTimeFormatter);

        Deadline test = new Deadline("Deadline Test Saved", dueDate);
        assertEquals("[D] // //Deadline Test Saved//Nov 11 2023 11:11//", test.convertToSavedString());

        test.markAsDone();
        assertEquals("[D] //X//Deadline Test Saved//Nov 11 2023 11:11//", test.convertToSavedString());

        test.markAsUndone();
        assertEquals("[D] // //Deadline Test Saved//Nov 11 2023 11:11//", test.convertToSavedString());
    }
}
