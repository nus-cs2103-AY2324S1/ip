package rua.common;

import org.junit.jupiter.api.Test;
import rua.task.Deadline;
import rua.task.Todo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void TaskToStringTest() throws Exception{
        String expected1 = "T | 1 | play";
        String expected2 = "D | 0 | test | 11 12 2023";

        assertEquals(expected1, Storage.taskToString(new Todo("play", true)));
        assertEquals(expected2, Storage.taskToString(new Deadline("test", LocalDate.parse("2023-11-12"))));

    }

    @Test
    public void StringToTaskTest() throws Exception{
        Todo expected1 = new Todo("play", true);
        Deadline expected2 = new Deadline("test", LocalDate.parse("2023-11-12"));
        assertEquals(expected1, Storage.stringToTask("T | 1 | play"));
        assertEquals(expected2, Storage.stringToTask("D | 0 | test | 11 12 2023"));
    }
}