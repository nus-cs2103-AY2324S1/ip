package duke;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void findTaskUsingKeyword_test1() throws FileNotFoundException {
        TaskList tasks = new TaskList(new Storage("data/loadTest.txt").load());
        ArrayList<Task> actualList = tasks.findTaskUsingKeyword("book");
        ArrayList<Task> expectedList = new ArrayList<>();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        String by = "20230607 1400";
        LocalDateTime dateTimeBy = LocalDateTime.parse(by, formatter1);

        expectedList.add(new ToDo("borrow book", true));
        expectedList.add(new ToDo("read book", false));
        expectedList.add(new Deadline("return book ", dateTimeBy, false));

        String actual = "";
        String expected = "";
        for (int i = 0; i < actualList.size(); i++) {
            actual += actualList.toString() + System.lineSeparator();
            expected += expectedList.toString() + System.lineSeparator();
        }

        assertEquals(expected, actual);

    }
}
