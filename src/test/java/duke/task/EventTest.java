package duke.task;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private String filePath = "./test";
    private Storage storage = new Storage(filePath, "");
    private Ui ui = new Ui("duke");
    private TaskList taskList = new TaskList(new ArrayList<>(100));
    @Test
    public void writeToFileEventTest() {
        try {
            storage.clearFile();
            Event event = Parser.getEvent("event hello /from 9am 26june /to 10am 26june");
            String expected = "E | 0 | hello | 2023-06-26T09:00 | 2023-06-26T10:00";
            String actual = event.writeToFile();

            assertEquals(actual, expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void toStringTest() {
        try {
            Event event = Parser.getEvent("event hello /from 9am 26june /to 10am 26june");
            String expected = "[E][ ] hello (from: 09:00 2023-06-26 to: 10:00 2023-06-26)";
            String actual = event.toString();

            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
