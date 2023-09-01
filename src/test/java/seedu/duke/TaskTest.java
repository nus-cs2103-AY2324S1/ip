package seedu.duke;  //same package as the class being tested

import DukeException.DukeException;
import List.TaskList;
import Tasks.Deadline;
import Parser.Parser;
import Tasks.Todo;
import org.junit.jupiter.api.Test;
import Command.Command;
import Command.TodoCommand;
import List.TaskList;
import Ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void simpleToDoTest(){
        String str = new Todo("book").toString();
        assertEquals(str,"  [T][ ] book");
    }

    @Test
    public void exceptionTest() throws DukeException {
        Parser parser = new Parser();
        try {
            Parser.parse("deadline book /by 10");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
        }
    }
}