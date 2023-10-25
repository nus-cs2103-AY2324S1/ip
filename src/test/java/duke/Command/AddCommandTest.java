package duke.Command;

import duke.command.AddCommand;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddCommandTest {
    private String filePath = "./test";
    private Storage storage = new Storage(filePath, "");
    private Ui ui = new Ui("duke");
    private TaskList taskList = new TaskList(new ArrayList<>(100));

    @Test
    public void toDoExecuteTest() {

        storage.clearFile();

        ToDo t = new ToDo("hello");
        String actualToDo = new AddCommand(t).execute(taskList, ui, storage);
        String expectedToDo = "Got it. I've added this task:\n" +
                "[T][ ] hello\n" +
                "Now you have 1 tasks in the list\n";
        assertEquals(expectedToDo, actualToDo);
    }
    @Test
    public void deadLineExecuteTest() {
        try {
            storage.clearFile();
            Command deadLine = new AddCommand(Parser.getDeadLine("deadline hello /by 9am 26june"));
            String actualDeadLine = deadLine.execute(taskList, ui, storage);
            String expectedDeadLine =
                    "Got it. I've added this task:\n" +
                    "[D][ ] hello (by: 09:00 2023-06-26)\n" +
                    "Now you have 1 tasks in the list\n";
            assertEquals(expectedDeadLine, actualDeadLine);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void eventExecuteTest() {
        try {
            storage.clearFile();
            Event event = Parser.getEvent("event hello /from 9am 26june /to 10am 26june");
            String actualEvent = new AddCommand(event).execute(taskList, ui, storage);
            String expectedEvent =
                    "Got it. I've added this task:\n" +
                    "[E][ ] hello (from: 09:00 2023-06-26 to: 10:00 2023-06-26)\n" +
                    "Now you have 1 tasks in the list\n";
            assertEquals(expectedEvent, actualEvent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
