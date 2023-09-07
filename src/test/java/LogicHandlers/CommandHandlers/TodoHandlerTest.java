package LogicHandlers.CommandHandlers;

import Models.TaskArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static Ui.BasicOutputPrinter.printBasicOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoHandlerTest {

    @Test
    public void parseCommandContentTest() {
        String commandContent = "Buy groceries";

        TaskArray taskArray = new TaskArray();
        TodoHandler todoHandler = new TodoHandler(taskArray);

        String expectedOutput = "Got it, I've added this task: \n" +
                "[T][ ] Buy groceries\n" +
                "You now have 1 tasks in the list.";

        assertEquals(expectedOutput, todoHandler.parseCommandContent(commandContent));
    }
}
