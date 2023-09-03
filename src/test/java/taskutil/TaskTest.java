package taskutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    private final LocalDateTime dateTime = LocalDateTime.parse("12.04.2020 1530", format);
    private final Deadline task2 = new Deadline("Finish homework", dateTime);
    private final Event task3 = new Event("Japan trip", dateTime, dateTime);
    private final Todo task1 = new Todo("Buy food");

    @Test
    public void changeStatus_toString_correctStatus() {
        task2.changeStatus(true);
        task3.changeStatus(true);
        Assertions.assertEquals("[T][ ] Buy food", task1.toString());
        Assertions.assertEquals("[D][X] Finish homework (by: 12 Apr 2020, 03:30 PM)", task2.toString());
        Assertions.assertEquals("[E][X] Japan trip (from: 12 Apr 2020, 03:30 PM to: 12 Apr 2020, 03:30 PM)",
                task3.toString());
    }
}
