package duke.main;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @Test
    public void addEvent_toDo_createNewTask(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Parser parser = new Parser(taskList, storage);
        parser.interact();
//        System.setIn("event do /from 2019-10-12 /to 2020-10-12");
    }
}
