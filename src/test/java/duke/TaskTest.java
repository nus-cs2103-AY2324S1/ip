package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskTest {
    @Test
    public void toSaveFormatTest() {
        Task[] inputs = {
            new Event("event1", "2023-09-01", "2023-09-01"),
            new Deadline("deadline1", "2023-09-01"),
            new Todo("todo1")
        };
        String[] expected = {
            "Event|0|event1||2023-09-01|2023-09-01",
            "Deadline|0|deadline1||2023-09-01",
            "Todo|0|todo1|"
        };
        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected[i], inputs[i].toSaveFormat());
        }
    }

    @Test
    public void isOnDateTest() {
        Task[] inputs = {
            new Event("event1", "2023-09-01", "2023-09-01"),
            new Deadline("deadline1", "2023-09-01"),
            new Todo("todo1")
        };
        LocalDate[] dates = {
            LocalDate.parse("2023-08-23"),
            LocalDate.parse("2023-09-01"),
            LocalDate.parse("2023-09-02")
        };
        boolean[][] expected = {{false, true, false}, {false, true, false}, {false, false, false}};
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < dates.length; j++) {
                assertEquals(expected[i][j], inputs[i].isOnDate(dates[j]));
            }
        }
    }
}
