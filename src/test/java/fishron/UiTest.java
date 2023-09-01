package fishron;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    @Test
    public void showDoneTaskTest() throws FishronException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo("Buy milk");
        taskList.addTask(todo);
        Command markCommand = new MarkDoneCommand(taskList.getSize());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        markCommand.execute(taskList, ui, new Storage("./data/fishron.txt"));

        System.setOut(System.out);

        String expectedDesc = "___________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "  " + todo.toString();

        String printedOutput = outContent.toString().trim();

        expectedDesc = expectedDesc.replaceAll("\\r\\n", "\n");
        printedOutput = printedOutput.replaceAll("\\r\\n", "\n");

        assertEquals(expectedDesc, printedOutput);
    }

}
