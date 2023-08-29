package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void wrongDate() {
        try {
            Parser.parse("deadline dance /by 12-12-2001-T0800", new UI("Alfred"), new TaskList(),
                    new Storage("src/main/java/duke/data/duke.txt"));
        } catch (DukeException e) {
            assertEquals("Invalid Date Format: should be YYYY-MM-DDTTime. " +
                                                   "Example: 2023-12-12T06:30:00", e.getMessage());
        }
    }

    @Test
    public void correctDate() throws DukeException {
        TaskList tasks = new TaskList();
        Parser.parse("deadline dance /by 2001-12-12T09:00:00", new UI("Alfred"), tasks ,
                new Storage("src/main/java/duke/data/duke.txt"));

        assertEquals("dance (by: DECEMBER 12 2001, 09:00)", tasks.get(tasks.size() - 1).toString());

    }
}
