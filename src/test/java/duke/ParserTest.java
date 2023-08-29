package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void wrongDateDeadLine() {
        try {
            Parser.parse("deadline dance /by 12-12-2001-T0800", new UI("Alfred"), new TaskList(),
                    new Storage("src/main/java/duke/data/duke.txt"));
        } catch (DukeException e) {
            assertEquals("Invalid Date Format: should be YYYY-MM-DDTTime. " +
                                                   "Example: 2023-12-12T06:30:00", e.getMessage());
        }
    }

    @Test
    public void correctDateDeadLine() throws DukeException {
        TaskList tasks = new TaskList();
        Parser.parse("deadline dance /by 2001-12-12T09:00:00", new UI("Alfred"), tasks ,
                new Storage("src/main/java/duke/data/duke.txt"));

        assertEquals("dance (by: DECEMBER 12 2001, 09:00)", tasks.get(tasks.size() - 1).toString());

    }

    @Test
    public void wrongCommand() {
        try {
            String wrongCommand = "jhfjd";
            Parser.parse(wrongCommand, new UI("John"), new TaskList(),
                    new Storage("src/main/java/duke/data/duke.txt"));
        } catch (DukeException e){
            assertEquals("I apologise, sir. But I do not understand what you mean.", e.getMessage());
        }
    }

    @Test
    public void addEmptyTodoDescription(){
        try {
            Parser.parse("todo", new UI("John"), new TaskList(),
                    new Storage("src/main/java/duke/data/duke.txt"));
        } catch (DukeException e){
            assertEquals("I apologise, sir. " +
                    "But the description of todo cannot be empty", e.getMessage());
        }
    }

    @Test
    public void addEmptyDeadLineDescription() {
        try {
            Parser.parse("deadline", new UI("John"), new TaskList(),
                    new Storage("src/main/java/duke/data/duke.txt"));
        } catch (DukeException e){
            assertEquals("I apologise, sir. " +
                    "But the description and deadline cannot be empty", e.getMessage());
        }
    }
}
