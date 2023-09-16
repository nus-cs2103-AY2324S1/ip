package sae.util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sae.exceptions.SaeException;
import sae.task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private TaskList taskList;
    private Parser parser;

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void checkList() {
        taskList = new TaskList();
        taskList.addToDoTask("sleep");
        LocalDateTime dateTime = LocalDateTime.of(2003, 01, 07, 15, 0);
        taskList.addDeadlineTask("sleep", dateTime);
        taskList.addEventTask("sleep", "6am", "12pm");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void invalidInputTest() throws SaeException {
        String expected =  "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String[] inputArray = new String[]{"UnknownInput"};

        parser.executeCommand(taskList, inputArray);
        assertEquals(expected.trim(), outContent.toString().trim());
    }

    @Test
    public void byeTest() throws SaeException{
        String expected = "Bye. Hope to see you again soon!";
        String[] inputArray = new String[]{"bye"};
        parser.executeCommand(taskList, inputArray);
        assertEquals(expected.trim(), outContent.toString().trim());
    }


}
