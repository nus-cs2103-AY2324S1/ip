package james;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void checkTaskParsing() throws JamesException {
        String[] taskInputs = {
            "todo read book",
            "deadline return book /by 2019-12-01 20:00",
            "event project meeting /from 2022-08-06 14:00 /to 2022-08-06 18:00",
            "todo join sports club",
            "todo borrow book"
        };
        String[] taskOutputs = {
            "[T][ ] read book",
            "[D][ ] return book (by: Dec 01 2019 20:00)",
            "[E][ ] project meeting (from: Aug 06 2022 14:00 to: Aug 06 2022 18:00)",
            "[T][ ] join sports club",
            "[T][ ] borrow book"
        };

        Parser parser = new Parser();

        for (int i = 0; i < taskInputs.length; i++) {
            Task task = parser.parseTask(taskInputs[i]);
            assertEquals(task.toString(), taskOutputs[i]);
        }

    }

    @Test
    public void checkParsing() {
        String[] taskInputs = {
            "todo read book",
            "deadline return book /by 2019-12-01 20:00",
            "event project meeting /from 2022-08-06 14:00 /to 2022-08-06 18:00",
            "todo join sports club",
            "todo borrow book"
        };
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        for (int i = 0; i < taskInputs.length; i++) {
            parser.parse(taskList, taskInputs[i]);
        }

        assertEquals(taskList.size(), taskInputs.length);
    }
}
