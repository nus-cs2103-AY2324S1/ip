package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.CommandList;
import duke.command.DeleteCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

public class ParserTest {
    @Test
    public void parseListCommand() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        CommandList commandList = new CommandList();
        Todo task1 = new Todo("read a book");
        taskList.addTask(task1);
        Deadline task2 = new Deadline("walk", "2002-12-01 12:12");
        taskList.addTask(task2);
        Event task3 = new Event("run 2.4km", "2022-12-12 12:00", "2022-12-12 14:00");
        taskList.addTask(task3);
        Parser parser = new Parser();
        try {
            TaskList initTaskList = storage.loadData();
            CommandList initCommandList = storage.previousCommandsLoader();
            AddCommand comm = (AddCommand) parser.parse(new String[] {"todo", "walk to school"}, taskList);
            comm.execute(taskList, storage, commandList, false);
            assertEquals(taskList.getTask(taskList.size() - 1).printTask(), "[T][ ] walk to school");
            assertEquals(commandList.size(), 0);
            for (int i = 0; i < 4; i++) {
                DeleteCommand revertChange = (DeleteCommand) parser.parse(new String[] {"delete",
                        Integer.toString(taskList.size() - i)}, taskList);
                revertChange.execute(taskList, storage, commandList, false);
            }
            assertEquals(taskList.size() - 1, 0);
            storage.writeData(initTaskList.convertToFileContent());
            storage.previousCommandsWriter(initCommandList.convertToFileContent());
        } catch (DukeException e) {
            System.out.println("Please check your test cases.");
        }
    }
}
