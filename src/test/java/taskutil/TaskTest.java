package taskutil;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    LocalDateTime dateTime = LocalDateTime.parse("12.04.2020 1530", format);
    Deadline task2 = new Deadline("Finish homework", dateTime);
    Event task3 = new Event("Japan trip", dateTime, dateTime);
    Todo task1 = new Todo("Buy food");

    @Test
    public void changeStatus_toString_correctStatus() {
        task2.changeStatus(true);
        task3.changeStatus(true);
        assertEquals("[T][ ] Buy food", task1.toString());
        assertEquals("[D][X] Finish homework (by: 12 Apr 2020, 03:30 PM)", task2.toString());
        assertEquals("[E][X] Japan trip (from: 12 Apr 2020, 03:30 PM to: 12 Apr 2020, 03:30 PM)"
                , task3.toString());
    }
}
