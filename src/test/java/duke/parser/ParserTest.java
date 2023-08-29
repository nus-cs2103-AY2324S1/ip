package duke.parser;


import duke.Duke;
import duke.task.ItemList;
import duke.task.deadline.DeadlineException;
import duke.task.event.Event;
import duke.task.todo.ToDoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    private ItemList items;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        this.items = new ItemList(new File(Duke.LISTPATH));
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parseToDoTest(){
        Parser parser = new Parser("todo newTODO");
        try {
            parser.parseTodo(this.items);
            outputStreamCaptor.reset();
            this.items.showitems();
            String expected = "____________________________________________________________\n" +
                    "Here are the tasks in your list:\n" +
                    "1. [T][ ] newTODO\n" +
                    "____________________________________________________________";
            String actualOutput = outputStreamCaptor.toString().trim();
            assertEquals(expected, actualOutput);
        } catch (ToDoException e) {
            fail();
        }

    }
    @Test
    public void parseDeadlineTest(){
        Parser parser = new Parser("deadline test /by 2/12/2019 1800");
        try {
            parser.parseDeadline(this.items);
            outputStreamCaptor.reset();
            this.items.showitems();
            String expected = "____________________________________________________________\n" +
                    "Here are the tasks in your list:\n" +
                    "1. [D][ ] test(by: 02 December 2019 18:00)\n" +
                    "____________________________________________________________";
            String actualOutput = outputStreamCaptor.toString().trim();
            assertEquals(expected, actualOutput);
        } catch (DeadlineException e) {
            fail();
        }

    }
}

