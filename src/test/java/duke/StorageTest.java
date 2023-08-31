package duke;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void textToTask_test1() throws DukeException {
        String expected = new ToDo("read book", false).toString();
        String actual = Storage.textToTask("T | [ ] | read book").toString();

        assertEquals(expected, actual);
    }

    @Test
    public void textToTask_test2() throws DukeException{
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
        String from = "20230808 1500";
        String to = "1600";
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter1);
        LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

        String expected = new Event("project meeting", dateTimeFrom, dateTimeTo, true).toString();
        String actual = Storage.textToTask("E | [X] | project meeting | 20230808 1500-1600").toString();

        assertEquals(expected, actual);
    }

    @Test
    public void load_test() throws FileNotFoundException {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
        String from = "20230808 1500";
        String to = "1600";
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter1);
        LocalTime dateTimeTo = LocalTime.parse(to, formatter2);
        String by = "20230607 1400";
        LocalDateTime dateTimeBy = LocalDateTime.parse(by, formatter1);

        Storage storage = new Storage("data/loadTest.txt");
        ArrayList<Task> actualList = storage.load();
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new ToDo("borrow book", true));
        expectedList.add(new ToDo("read book", false));
        expectedList.add(new Deadline("return book ", dateTimeBy, false));
        expectedList.add(new Event("project meeting ", dateTimeFrom, dateTimeTo, false));

        String actual = "";
        String expected = "";
        for (int i = 0; i < actualList.size(); i++) {
            actual += actualList.toString() + System.lineSeparator();
            expected += expectedList.toString() + System.lineSeparator();
        }

        assertEquals(expected, actual);
    }

}
