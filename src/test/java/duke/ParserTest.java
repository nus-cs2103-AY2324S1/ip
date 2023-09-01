package duke;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseTest() {
        String[] inputs = {"deadline return book /by 2023-09-01",
                "event project meeting /from 2023-09-01 /to 2023-09-01"};
        Command[] expecteds = {
                new Command(Arrays.asList("deadline", "return book", "2023-09-01")),
                new Command(Arrays.asList("event", "project meeting", "2023-09-01", "2023-09-01"))
        };
        for (int i = 0; i < inputs.length; i++) {
            Command c = Parser.parse(inputs[i]);
            assertTrue(expecteds[i].equals(c));
        }
    }

    @Test
    public void invalidParseTest() {
        String input = "random thing";
        Command expected = new Command(Arrays.asList("randomthing"));
        assertTrue(expected.equals(Parser.parse(input)));
    }


    @Test void parseTaskTest() {
        String[][] inputs = {{"deadline", "return book", "2023-09-01"},
                {"event", "project meeting", "2023-09-01", "2023-09-01"}};
        Task[] expected = {
                new Deadline("return book", "2023-09-01"),
                new Event("project meeting", "2023-09-01", "2023-09-01")
        };
        for (int i = 0; i < inputs.length; i++) {
            try {
                Task c = Parser.parseTask(inputs[i]);
                assertTrue(expected[i].equals(c));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
