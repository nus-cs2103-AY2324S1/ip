package dre.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testFileSaveFormat1() {
        ToDo testDeadline = new ToDo("find a boeing");
        assertEquals(testDeadline.fileSaveFormat(), "T| |find a boeing");
    }

    @Test
    public void testFileSaveFormat2() {
        //test space trim
        ToDo testDeadline = new ToDo("    extra spaces");
        assertEquals(testDeadline.fileSaveFormat(), "T| |extra spaces");
    }

    @Test
    public void testFileSaveFormat3() {
        //test different date format entry
        ToDo testDeadline = new ToDo("learn to fly plane");
        assertEquals(testDeadline.fileSaveFormat(), "T| |learn to fly plane");
    }

    @Test
    public void testToString() {
        ToDo testDeadline = new ToDo("learn to fly plane");
        assertEquals(testDeadline.toString(), "[T][ ] learn to fly plane");
    }
}
