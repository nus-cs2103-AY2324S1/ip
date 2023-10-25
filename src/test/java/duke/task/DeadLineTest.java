package duke.task;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadLineTest {
    private String filePath = "./test";
    private Storage storage = new Storage(filePath, "");
    private Ui ui = new Ui("duke");
    private TaskList taskList = new TaskList(new ArrayList<>(100));
    @Test
    public void writeToFileDeadLineTest() {
        try {
            storage.clearFile();
            DeadLine deadLine = Parser.getDeadLine("deadline hello /by 9am 26june");
            String expected = "D | 0 | hello | 2023-06-26T09:00";
            String actual = deadLine.writeToFile();
            assertEquals(actual, expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void toStringTest() {
        try {
            DeadLine deadLine = Parser.getDeadLine("deadline hello /by 9am 26june");
            String expected = "[D][ ] hello (by: 09:00 2023-06-26)";
            String actual = deadLine.toString();

            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
