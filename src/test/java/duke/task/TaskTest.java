package duke.task;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void taskDoneTest() {
        Task task = new Task("sleep");
        task.setTaskDone();
        assertEquals("X",task.getStatus());
    }

    @Test
    void taskStatusFromFileTest() {
        Task task = new Task("sleep");
        task.taskStatusFromFile(true);
        assertEquals("X",task.getStatus());
    }

    @Test
    void undoTaskTest() {
        Task task = new Task("sleep");
        task.setTaskDone();
        task.undoTask();
        assertEquals(" ",task.getStatus());
    }

    @Test
    void convertStringToDateTest1() {
        Task task = new Task("sleep");
        LocalDate date = LocalDate.of(2020, 9, 18);
        assertEquals(date,task.convertStringToDate("2020.09.18"));
    }

    @Test
    void convertStringToDateTest2() {
        Task task = new Task("sleep");
        LocalDate date = LocalDate.of(2020, 12, 12);
        assertEquals(date,task.convertStringToDate("2020/12/12"));
    }

    @Test
    void convertStringToDay1() {
        Task task = new Task("sleep");
        assertEquals(DayOfWeek.TUESDAY,task.convertStringToDay("Tue"));
    }

    @Test
    void convertStringToDay2() {
        Task task = new Task("sleep");
        assertEquals(DayOfWeek.FRIDAY,task.convertStringToDay("Friday"));
    }

    @Test
    void convertStringToTime1() {
        Task task = new Task("sleep");
        LocalTime time= LocalTime.of(4,30);
        assertEquals(time,task.convertStringToTime("430AM"));
    }

    @Test
    void convertStringToTime2() {
        Task task = new Task("sleep");
        LocalTime time = LocalTime.of(16, 00);
        assertEquals(time,task.convertStringToTime("4PM"));
    }

    @Test
    void dateToStringTest() {
        Task task = new Task("sleep");
        LocalDate date = LocalDate.of(2020, 12, 12);
        assertEquals("12 Dec 2020", task.dateToString(date));
    }

    @Test
    void dayToStringTest() {
        Task task = new Task("sleep");
        DayOfWeek day = DayOfWeek.MONDAY;
        assertEquals(", Monday",task.dayToString(day));
    }

    @Test
    void timeToStringTest() {
        Task task = new Task("sleep");
        LocalTime time = LocalTime.of(16,00);
        assertEquals(", 4.00PM",task.timeToString(time));
    }

    @Test
    void testToString() {
        Task task = new Task("sleep");
        task.setTaskDone();
        assertEquals(" [X] sleep", task.toString());
    }

    @Test
    void toFileString() {
        Task task = new Task("sleep");
        assertEquals("|N|sleep", task.toFileString());
    }
}