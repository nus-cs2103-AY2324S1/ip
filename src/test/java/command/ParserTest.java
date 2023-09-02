package command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParserTest {

    @Mock
    protected TaskList taskListMock;
    @InjectMocks
    protected Parser parser;

    public ParserTest() {
        taskListMock = mock(TaskList.class);
        parser = new Parser(taskListMock);
    }

    @Test
    public void execute_validInput_success() {
        try {
            when(taskListMock.add("test", CommandType.TODO))
                    .thenReturn("add method called with todo task");
            when(taskListMock.add("test", CommandType.DEADLINE, "2020-08-25 00:00"))
                    .thenReturn("add method called with deadline task");
            when(taskListMock.delete(1)).thenReturn("delete method called");

            assertEquals("add method called with todo task", parser.execute("todo", "test"));
            assertEquals("add method called with deadline task",
                    parser.execute("deadline", "test      /by   2020-08-25 00:00"));
            assertEquals("delete method called", parser.execute("delete", "1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_invalidCommand_exceptionThrown() {
        try {
            parser.execute("invalid_command", "test");
        } catch (Exception e) {
            assertEquals("Sorry, sir. Executing this command (invalid_command) is beyond my capabilities.",
                    e.getMessage());
        }
    }

    @Test
    public void execute_invalidArgument_exceptionThrown() {
        // when the number of argument provided is incorrect.
        try {
            parser.execute("event", "no time specified");
        } catch (Exception e) {
            assertEquals("Sir, please check again to ensure you provide "
                    + "the correct arguments for command (event).", e.getMessage());
        }

        // when the argument cannot be parsed into an integer.
        try {
            parser.execute("mark", "string instead of integer");
        } catch (Exception e) {
            assertEquals("Sir, please check again to ensure you provide "
                    + "the correct arguments for command (mark).", e.getMessage());
        }
    }

    @Test
    public void execute_emptyArgument_exceptionThrown() {
        try {
            parser.execute("mark", "");
        } catch (Exception e) {
            assertEquals("Sir, I did not catch what you say after the command (mark).\nI beg your pardon.",
                    e.getMessage());
        }
    }
}
