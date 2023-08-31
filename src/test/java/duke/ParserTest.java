package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void decideCommand_invalidInput_invalid() {
        assertEquals(Command.INVALID, Parser.decideCommand("abc 123"));
    }

    @Test
    public void decideCommand_wrongByeFormat_invalid() {
        assertEquals(Command.INVALID, Parser.decideCommand("bye bye"));
    }

    @Test
    public void decideCommand_wrongListInput_invalid() {
        assertEquals(Command.INVALID, Parser.decideCommand("list 123"));
    }

    @Test
    public void decideCommand_eventInput_event() {
        assertEquals(Command.EVENT, Parser.decideCommand("event abc"));
    }

    @Test
    public void decideCommand_deadlineInput_deadline() {
        assertEquals(Command.DEADLINE, Parser.decideCommand("deadline abc"));
    }

    @Test
    public void decideCommand_todoInput_todo() {
        assertEquals(Command.TODO, Parser.decideCommand("todo abc"));
    }

    @Test
    public void decideCommand_markInput_mark() {
        assertEquals(Command.MARK, Parser.decideCommand("mark abc"));
    }

    @Test
    public void decideCommand_unmarkInput_unmark() {
        assertEquals(Command.UNMARK, Parser.decideCommand("unmark abc"));
    }

    @Test
    public void decideCommand_correctListInput_list() {
        assertEquals(Command.LIST, Parser.decideCommand("list"));
    }

    @Test
    public void decideCommand_correctByeInput_bye() {
        assertEquals(Command.BYE, Parser.decideCommand("bye"));
    }

    @Test
    public void stringToTask_incorrectTaskType_exceptionThrown() {
        try {
            assertEquals(new Todo("abc").toString(),
                    Parser.stringToTask("a | 1 | abc").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Field 1 (Task type) is invalid", e.getMessage());
        }
    }

    @Test
    public void stringToTask_incorrectIsMarked_exceptionThrown() {
        try {
            assertEquals(new Todo("abc").toString(), Parser.stringToTask("T | 3 | abc").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Field 2 (isMarked) is invalid", e.getMessage());
        }
    }

    @Test
    public void stringToTask_incorrectTodoFormat_exceptionThrown() {
        try {
            assertEquals(new Todo("abc").toString(), Parser.stringToTask("T | 1").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Incorrect Format for todo task in file", e.getMessage());
        }
    }

    @Test
    public void stringToTask_incorrectDeadlineFormat_exceptionThrown() {
        try {
            assertEquals(new Deadline(LocalDate.of(2023, 6, 23), "abc").toString(),
                    Parser.stringToTask("D | 0 | abc").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Incorrect Format for deadline task in file", e.getMessage());
        }
    }

    @Test
    public void stringToTask_incorrectEventFormat_exceptionThrown() {
        try {
            assertEquals(new Event(LocalDate.of(2023, 6, 23),
                            LocalDate.of(2023, 6, 24),
                            "abc").toString(),
                    Parser.stringToTask("E | 0 | abc | 2023-06-23").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Incorrect Format for event task in file", e.getMessage());
        }
    }

    @Test
    public void stringToTask_correctTodoFormat_todoTask() {
        try {
            assertEquals(new Todo("abc").toString(),
                    Parser.stringToTask("T | 0 | abc").toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void stringToTask_correctDeadlineFormat_deadlineTask() {
        try {
            assertEquals(new Deadline(LocalDate.of(2023, 6, 23), "abc").toString(),
                    Parser.stringToTask("D | 0 | abc | 2023-06-23").toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void stringToTask_correctEventFormat_eventTask() {
        try {
            assertEquals(new Event(LocalDate.of(2023, 6, 23),
                            LocalDate.of(2023, 6, 24), "abc").toString(),
                    Parser.stringToTask("E | 0 | abc | 2023-06-23 | 2023-06-24").toString());
        } catch (Exception e) {
            fail();
        }
    }
}
