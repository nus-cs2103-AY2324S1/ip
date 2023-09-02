package duke.main;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.TaskList;
import duke.task.Todos;
import duke.task.Deadlines;
import duke.task.Events;


public class ParserTest {
    @Test
    public void parseListCommand() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        Todos task1 = new Todos("read a book");
        taskList.addTask(task1);
        Deadlines task2 = new Deadlines("walk", "2002-12-01 12:12");
        taskList.addTask(task2);
        Events task3 = new Events("run 2.4km", "2022-12-12 12:00", "2022-12-12 14:00");
        taskList.addTask(task3);
        Parser parser = new Parser();
        try {
            AddCommand comm = (AddCommand) parser.parse(new String[] {"todo", "walk to school"}, taskList);
            comm.execute(taskList, storage);
            assertEquals(taskList.getTask(taskList.size() - 1).printTask(), "[T][ ] walk to school");
        } catch (DukeException e) {
            System.out.println("Please check your test cases.");
        }
        storage.writeData("");
    }
}
