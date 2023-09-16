package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.Command;
import exceptions.FishronException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class AddCommandTest {

    @Test
    public void execute_addTodo_success() throws FishronException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Command addCommand = parser.parse("todo Buy groceries", taskList);

        addCommand.execute(taskList, new Ui(), new Storage("./data/fishron.txt"));

        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Buy groceries", taskList.getTask(1).toString());
    }
}
