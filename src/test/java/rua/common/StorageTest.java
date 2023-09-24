package rua.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import rua.task.Deadline;
import rua.task.Todo;

public class StorageTest {
    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void taskToStringTest() throws Exception {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("#fun");
        String expected1 = "T | 1 | play | ";
        String expected2 = "D | 0 | test | #fun | 11 12 2023 08:00";

        assertEquals(expected1, Storage.taskToString(new Todo("play", true)));
        assertEquals(expected2, Storage.taskToString(new Deadline("test",
                LocalDateTime.parse("2023-11-12 08:00", INPUT_FORMATTER), false, tags)));
    }

    @Test
    public void stringToTaskTest() throws Exception {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("#fun");
        Todo expected1 = new Todo("play", true, tags);
        Deadline expected2 = new Deadline("test",
                LocalDateTime.parse("2023-11-12 08:00", INPUT_FORMATTER));
        assertEquals(expected1, Storage.stringToTask("T | 1 | play | #fun"));
        assertEquals(expected2, Storage.stringToTask("D | 0 | test |  | 11 12 2023 08:00"));
    }
}
