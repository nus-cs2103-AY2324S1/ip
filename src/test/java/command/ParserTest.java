package command;

import org.junit.jupiter.api.Test;
import services.tasklist.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    protected TaskListStub taskListStub;
    protected Parser parser;
    public ParserTest() {
        taskListStub = new TaskListStub();
        parser = new Parser(taskListStub);
    }

    @Test
    public void execute_validInput_success() {
        try {
            parser.execute("todo", "test");
            assertEquals("add method called with description: test and taskType: TODO and args: [].",
                    taskListStub.getStatus());
            parser.execute("delete", "1");
            assertEquals("delete method called with taskNumber: 1.", taskListStub.getStatus());
            parser.execute("deadline", "test   /by      2020-08-25 00:00");
            assertEquals("add method called with description: test and taskType: DEADLINE "
                            + "and args: [2020-08-25 00:00].",
                    taskListStub.getStatus());
            parser.execute("list", "");
            assertEquals("show method called", taskListStub.getStatus());
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
