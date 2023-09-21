package ari;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void findTaskUsingKeyword_test1() throws IOException {
        TaskList tasks = new TaskList(new Storage("data/loadTest.txt").load());
        ArrayList<Task> actualList = tasks.findTaskUsingKeyword("book");
        ArrayList<Task> expectedList = new ArrayList<>();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        String by = "20230607 1400";
        LocalDateTime dateTimeBy = LocalDateTime.parse(by, formatter1);

        expectedList.add(new ToDo("borrow book", true));
        expectedList.add(new ToDo("read book", false));
        expectedList.add(new Deadline("return book", dateTimeBy, false));

        String actual = "";
        String expected = "";
        for (int i = 0; i < actualList.size(); i++) {
            actual += actualList.toString() + System.lineSeparator();
            expected += expectedList.toString() + System.lineSeparator();
        }

        assertEquals(expected, actual);

    }

    @Test
    public void postponeTask_throwDukeException() throws IOException {
        TaskList tasks = new TaskList(new Storage("data/loadTest.txt").load());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        String by = "20230608 1600";
        LocalDateTime dateTimeBy = LocalDateTime.parse(by, formatter);

        assertThrows(AriException.class, () -> tasks.postponeDeadline(0, dateTimeBy));

    }

    @Test
    public void postponeTask_test() throws IOException, AriException {
        TaskList tasks = new TaskList(new Storage("data/loadTest.txt").load());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        String by = "20230608 1600";
        LocalDateTime dateTimeBy = LocalDateTime.parse(by, formatter);

        tasks.postponeDeadline(2, dateTimeBy);

        String expected = "[D][ ] return book (by: Jun 08 2023 4PM)";
        String actual = tasks.getTasks().get(2).toString();

        assertEquals(expected, actual);

    }

    @Test
    public void rescheduleTask_throwDukeException() throws IOException {
        TaskList tasks = new TaskList(new Storage("data/loadTest.txt").load());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        String from = "20230809 1500";
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
        String to = "1600";
        LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

        assertThrows(AriException.class, () -> tasks.rescheduleEvent(1, dateTimeFrom, dateTimeTo));
    }

    @Test
    public void rescheduleTask_test() throws IOException, AriException{
        TaskList tasks = new TaskList(new Storage("data/loadTest.txt").load());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        String from = "20230809 1500";
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
        String to = "1600";
        LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

        tasks.rescheduleEvent(3, dateTimeFrom, dateTimeTo);

        String expected = "[E][ ] project meeting (from: Aug 09 2023 3PM to: 4PM)";
        String actual = tasks.getTasks().get(3).toString();

        assertEquals(expected, actual);
    }
}