package functions;

import org.junit.jupiter.api.Test;
import tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parserTest1() {
        TaskList taskList = new TaskList();
        String input = "";
        try {
            Parser parser = new Parser(input, taskList);
            assertEquals(parser.parse(), "Oops!!! I'm sorry but I don't know what that means :-( \n"
                    + "Run help to get a list of available commands.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parserTest2() {
        TaskList taskList = new TaskList();
        String input = " ";
        try {
            Parser parser = new Parser(input, taskList);
            assertEquals(parser.parse(), "I did not understand that! Run help to get a "
                    + "list of available commands.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parserTest3() {
        TaskList taskList = new TaskList();
        String input = "asojongjnfdsj";
        try {
            Parser parser = new Parser(input, taskList);
            assertEquals(parser.parse(), "Oops!!! I'm sorry but I don't know what that means :-( \n"
                    + "Run help to get a list of available commands.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parserTest4() {
        TaskList taskList = new TaskList();
        String input = "todo eat";
        try {
            Parser parser = new Parser(input, taskList);
            assertEquals(parser.parse(), "I have added the following: \n" + "[T][ ] eat");
        } catch (Exception e) {
            fail();
        }
    }
}
