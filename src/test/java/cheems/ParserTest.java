package cheems;

import cheems.exceptions.EmptyArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ParserTest {
    UI uiMock = mock(UI.class);
    Tasklist tasklistMock = mock(Tasklist.class);
    private Parser parser = new Parser(tasklistMock, uiMock);
    @Test
    void parseAndExecute_emptyInput_success() throws Exception {
        parser.parseAndExecute("");

        String resp = "Please give me instructions, if not, I'll serve you some fries.";
        verify(uiMock).printWithFormat(resp);
    }

    @Test
    void parseAndExecute_validFind_tasklistMethodCalled() {
        String input = "find book";

        parser.parseAndExecute(input);

        verify(tasklistMock).find("book");
    }

    @Test
    void parseAndExecute_validMark_tasklistMethodCalled() {
        String input = "mark 1";

        parser.parseAndExecute(input);

        verify(tasklistMock).markAsDone(0);
    }

    @Test
    void parseAndExecute_validUnmark_tasklistMethodCalled() {
        String input = "unmark 1";

        parser.parseAndExecute(input);

        verify(tasklistMock).markAsNotDone(0);
    }
    @Test
    void parseAndExecute_validDelete_tasklistMethodCalled() {
        String input = "delete 1";

        parser.parseAndExecute(input);

        verify(tasklistMock).delete(0);
    }

    @Test
    void parseAndExecute_validTodo_tasklistMethodCalled() {
        String input = "todo ballet lesson";

        parser.parseAndExecute(input);

        verify(tasklistMock).addTaskToDatabase("TODO", "ballet lesson");
    }

    @Test
    void parseAndExecute_validEvent_tasklistMethodCalled() {
        String input = "event global conference /from 2023-04-29 /to 2023-05-12";

        parser.parseAndExecute(input);

        verify(tasklistMock).addTaskToDatabase("EVENT", "global conference", "2023-04-29", "2023-05-12");
    }

    @Test
    void parseAndExecute_validDeadline_tasklistMethodCalled() {
        String input = "deadline CS2100 quiz /by 2023-09-01";

        parser.parseAndExecute(input);

        verify(tasklistMock).addTaskToDatabase("DEADLINE","CS2100 quiz","2023-09-01");
    }

    @Test
    void parseAndExecute_noArguments_exceptionThrown() {
        assertThrows(EmptyArgumentException.class, () -> {
            parser.parseAndExecute("find");
        });

        assertThrows(NumberFormatException.class, () -> {
            parser.parseAndExecute("mark something");
        });

    }
}