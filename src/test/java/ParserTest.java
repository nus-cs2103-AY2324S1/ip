
import duke.exceptions.InvalidTaskException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import duke.Parser;
import duke.command.*;
import duke.Storage;
import duke.Ui;
import duke.task.*;

public class ParserTest {

    @Test
    void testDate1() {
        Parser parser = new Parser();
        try {
            TaskList taskList = new TaskList();

            Command command = parser.parse("deadline find wedding ring /by 2020-09-09");
            command.execute(new Storage(""), new Ui(), taskList);
            System.out.println(taskList);
            assertEquals("1. [D][ ] find wedding ring (by: Sep 9 2020)", taskList.toString());
        } catch (InvalidTaskException e ) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testDate2() {
        Parser parser = new Parser();
        try {
            TaskList taskList = new TaskList();

            Command command = parser.parse("deadline find wedding ring /by 2020-09-09");
            command.execute(new Storage(""), new Ui(), taskList);
            Command command2 = parser.parse("todo follow the leader");
            command2.execute(new Storage(""), new Ui(), taskList);
            assertEquals(
                    "1. [D][ ] find wedding ring (by: Sep 9 2020)\n" +
                    "2. [T][ ] follow the leader",
                    taskList.toString());
        } catch (InvalidTaskException e ) {
            System.out.println(e.getMessage());
        }
    }
}
