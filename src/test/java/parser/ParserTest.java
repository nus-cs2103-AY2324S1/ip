package parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import commands.Command;
import errors.DotException;
import tasks.TaskList;

public class ParserTest {

    @Test
    public void parseInputToCommand_validTodoCommand_success() {
        try {
            TaskList dotTaskList = TaskList.newTaskList(100);
            String dataFilePathname = "src/main/java/data/dot.txt";
            Command cmd = Parser.parseInputToCommand("todo "
                + " send email", dotTaskList, dataFilePathname);
        } catch (DotException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseInputToCommand_validDeadlineCommand_success() {
        try {
            TaskList dotTaskList = TaskList.newTaskList(100);
            String dataFilePathname = "src/main/java/data/dot.txt";
            Command cmd = Parser.parseInputToCommand("deadline cs2103t "
                + "ip /by 30/8/2023 2359", dotTaskList, dataFilePathname);
        } catch (DotException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseInputToCommand_validEventCommand_success() {
        try {
            TaskList dotTaskList = TaskList.newTaskList(100);
            String dataFilePathname = "src/main/java/data/dot.txt";
            Command cmd = Parser.parseInputToCommand("event carnival "
                + "/from 12/3/2022 1800 /to 12/4/2022 1600",
                dotTaskList, dataFilePathname);
        } catch (DotException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseInputToCommand_invalidDeadlineCommand_exceptionThrown() {
        try {
            TaskList dotTaskList = TaskList.newTaskList(100);
            String dataFilePathname = "src/main/java/data/dot.txt";
            Command cmd = Parser.parseInputToCommand("deadline cs2103t "
                + "ip /by", dotTaskList, dataFilePathname);
        } catch (DotException e) {
            Assertions.assertEquals("No deadline description given.",
                e.getMessage());
        }
    }

}
