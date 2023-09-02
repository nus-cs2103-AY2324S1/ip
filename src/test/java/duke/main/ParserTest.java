package duke.main;

import org.junit.jupiter.api.Test;
public class ParserTest {

    @Test
    public void addEvent_toDo_createNewTask() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Parser parser = new Parser(taskList, storage);
        // parser.interact();
        // assert true;
    }
}
