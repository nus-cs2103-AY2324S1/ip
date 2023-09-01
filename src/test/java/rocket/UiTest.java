package rocket;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    @Test
    public void showTasks_sampleTasks_success(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("[T][ ] borrow book");
        stringList.add("[D][ ] return book (by: Oct 15 2023, 7:45 PM)");
        stringList.add("[E][ ] project meeting (from: Oct 16 2023, 2:00 PM to: Oct 16 2023, 4:00 PM)");
        TaskList tasks;
        try {
            tasks = new TaskList(stringList);
        } catch (RocketIllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        Ui ui = new Ui();
        ui.showTasks(tasks);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        ui.showTasks(tasks);

        System.setOut(originalOut);
        String printedOutput = outputStream.toString();

        String expectedOutput = "    1.[T][ ] borrow book\n" +
                "    2.[D][ ] return book (by: Oct 15 2023, 7:45 PM)\n" +
                "    3.[E][ ] project meeting (from: Oct 16 2023, 2:00 PM to: Oct 16 2023, 4:00 PM)\n";

        assertEquals(expectedOutput, printedOutput);



    }

}
