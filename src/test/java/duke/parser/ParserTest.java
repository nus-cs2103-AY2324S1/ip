package duke.parser;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import duke.Duke;
import duke.task.ItemList;
import duke.task.deadline.DeadlineException;
import duke.task.todo.ToDoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    private ItemList items;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        this.items = new ItemList(new File(Duke.LISTPATH));
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parseToDoTest_normalInput(){
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
    public void parseToDoTest_noNameInput_throwTodoException(){
        Parser parser = new Parser("todo");
        try {
            parser.parseTodo(this.items);
            outputStreamCaptor.reset();
            this.items.showitems();
            fail();
        } catch (ToDoException ignored) {

        }

    }
    @Test
    public void parseDeadlineTest_dateInput(){
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
    @Test
    public void parseDeadlineTest_normalInput(){
        Parser parser = new Parser("deadline test /by today");
        try {
            parser.parseDeadline(this.items);
            outputStreamCaptor.reset();
            this.items.showitems();
            String expected = "____________________________________________________________\n" +
                    "Here are the tasks in your list:\n" +
                    "1. [D][ ] test(by: today)\n" +
                    "____________________________________________________________";
            String actualOutput = outputStreamCaptor.toString().trim();
            assertEquals(expected, actualOutput);
        } catch (DeadlineException e) {
            fail();
        }

    }

    @Test
    public void parseDeadlineTest_invalidInput_throwDeadlineException(){
        Parser parser = new Parser("deadline test /by");
        try {
            parser.parseDeadline(this.items);
            outputStreamCaptor.reset();
            this.items.showitems();
            fail();
        } catch (DeadlineException ignored) {

        }

    }


}

