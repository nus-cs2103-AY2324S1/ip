package dre.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testFileSaveFormat1() {
        Deadline testDeadline = new Deadline("learn to fly plane", LocalDate.parse("2001-09-11"));
        assertEquals(testDeadline.fileSaveFormat(), "D| |learn to fly plane|2001-09-11");
    }

    @Test
    public void testFileSaveFormat2() {
        Deadline testDeadline = new Deadline("    extra spaces", LocalDate.parse("2001-09-11"));
        assertEquals(testDeadline.fileSaveFormat(), "D| |    extra spaces|2001-09-11");
    }

    @Test
    public void testFileSaveFormat3() {
        Deadline testDeadline = new Deadline("learn to fly plane", LocalDate.parse("2001-01-31"));
        assertEquals(testDeadline.fileSaveFormat(), "D| |learn to fly plane|2001-01-31");
    }

    @Test
    public void testToString() {
        Deadline testDeadline = new Deadline("learn to fly plane", LocalDate.parse("2001-09-11"));
        assertEquals(testDeadline.toString(), "[D][ ] learn to fly plane (by: Sep 11 2001)");
    }
}
