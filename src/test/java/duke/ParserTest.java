package duke;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deadlineCommandTest () {
        Parser parser = new Parser();
        String command = "deadline a deadline here /by 2022-02-01";
        String result = parser.parseCommand(command);
        assertEquals("deadline", result);
    }
    @Test
    public void eventCommandTest() {
        Parser parser = new Parser();
        String command = "event an event here /from 2022-02-01 /to 2022-02-01";
        String result = parser.parseCommand(command);
        assertEquals("event", result);
    }
    @Test
    public void todoCommandTest() {
        Parser parser = new Parser();
        String command = "todo a todo here";
        String result = parser.parseCommand(command);
        assertEquals("todo", result);
    }
    @Test
    public void deadlineDescriptionTest () {
        Parser parser = new Parser();
        String command = "deadline a deadline here /by 2022-02-01";
        parser.parseCommand(command);
        Task result = parser.parseToTask();
        String description = result.description;
        assertEquals("a deadline here ", description);
    }
}
