package dot.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dot.commands.Command;
import dot.errors.DotException;
import dot.storage.Storage;
import dot.tasks.TaskList;

public class ParserTest {

    @Test
    public void parseInputToCommand_validTodoCommand_success() {
        try {
            Storage storage = new Storage("./data/junit-test-data.txt");
            TaskList dotTaskList = TaskList.getNewTaskList(100, storage);
            Command cmd = Parser.parseInputToCommand("todo "
                    + " send email", dotTaskList);
            cmd.execute();
        } catch (DotException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseInputToCommand_validDeadlineCommand_success() {
        try {
            Storage storage = new Storage("./data/junit-test-data.txt");
            TaskList dotTaskList = TaskList.getNewTaskList(100, storage);
            Command cmd = Parser.parseInputToCommand("deadline cs2103t "
                    + "ip /by 30/8/2023 2359", dotTaskList);
            cmd.execute();
        } catch (DotException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseInputToCommand_validEventCommand_success() {
        try {
            Storage storage = new Storage("./data/junit-test-data.txt");
            TaskList dotTaskList = TaskList.getNewTaskList(100, storage);
            Command cmd = Parser.parseInputToCommand("event carnival "
                            + "/from 12/3/2022 1800 /to 12/4/2022 1600",
                    dotTaskList);
            cmd.execute();
        } catch (DotException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseInputToCommand_invalidDeadlineCommand_exceptionThrown() {
        try {
            Storage storage = new Storage("./data/junit-test-data.txt");
            TaskList dotTaskList = TaskList.getNewTaskList(100, storage);
            Command cmd = Parser.parseInputToCommand("deadline cs2103t "
                    + "ip /by", dotTaskList);
            cmd.execute();
        } catch (DotException e) {
            Assertions.assertEquals("No deadline description given.",
                    e.getMessage());
        }
    }

}
