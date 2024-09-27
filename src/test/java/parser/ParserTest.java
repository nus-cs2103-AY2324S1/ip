package parser;

import ekud.exceptions.EkudException;
import ekud.exceptions.EkudIllegalArgException;
import ekud.parser.Parser;
import ekud.tasks.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseInput_emptyInput_runsWithoutException() {
        try {
            Parser parser = new Parser();
            String[] expectedOutput = new String[]{"", ""};
            String[] actualOutput = parser.parseInput("");
            assertEquals(true, Arrays.equals(expectedOutput, actualOutput));
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void parseUserDateTime_correctFormat_success() {
        Parser parser = new Parser();
        LocalDateTime expectedOutput = LocalDateTime.of(2023, 8, 10, 17, 30);
        LocalDateTime actualOutput = parser.parseDateTime("10 Aug 1730");
        assertEquals(true, expectedOutput.isEqual(actualOutput));
    }
    @Test
    public void parseUserDateTime_incorrectFormat_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class,
                () -> {
                    Parser parser = new Parser();
                    parser.parseDateTime("10/08/23 5.30pm");
                });
    }
    @Test
    public void parseSavedDateTime_correctFormat_success() {
        Parser parser = new Parser();
        LocalDateTime expectedOutput = LocalDateTime.of(2023, 10, 1, 17, 00);
        LocalDateTime actualOutput = parser.parseSavedDateTime("01 Oct 2023 5:00 PM");
        assertEquals(true, expectedOutput.isEqual(actualOutput));
    }
    @Test
    public void parseAndExecute_invalidCommand_throwsEkudException() {
        assertThrows(EkudException.class,
                () -> {
                    Parser parser = new Parser();
                    parser.parseAndExecute(new TaskList(), "invalidCommand", "arguments");
                });
    }
    @Test
    public void parseAndExecute_validAddTodo_success() {
        Parser parser = new Parser();
        try {
            TaskList taskList = new TaskList();
            parser.parseAndExecute(taskList, "todo", "test description");
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void parseAndExecute_taskNumOutOfBounds_throwsEkudException() {
        assertThrows(EkudException.class,
                () -> {
                    Parser parser = new Parser();
                    TaskList taskList = new TaskList();
                    taskList.addToDo("test description");
                    parser.parseAndExecute(taskList, "mark", "2");
                });
    }
}