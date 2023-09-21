package duke;

import gman.Parser;
import gman.TaskList;
import gman.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void exitCommandTest() {
        Assertions.assertEquals("Bye. Hope to see you again soon!", Parser.exitCommand(new TaskList()));
    }

    /**
     * Test works only when gman.txt in ip/data is blank.
     */
    @Test
    public void listCommandTest() {
        Assertions.assertEquals("There's nothing to print in the list bozo...",
                Parser.listCommand(new TaskList()));
        TaskList taskList = new TaskList();
        Todo todo = new Todo(" HAVE FUN");
        taskList.addTask(todo);
        System.out.println(Parser.listCommand(taskList));
        Assertions.assertEquals("Here are the tasks in your list:\n1. [T][ ] HAVE FUN\n",
                Parser.listCommand(taskList));
    }


}
