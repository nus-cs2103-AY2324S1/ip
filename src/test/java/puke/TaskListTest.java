package puke;

import org.junit.jupiter.api.Test;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.task.Task;
import puke.task.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListTests() throws PukeException {
        TaskList testList1 = new TaskList();
        ArrayList<Task> listInput = new ArrayList<Task>();
        listInput.add(new ToDo("a"));
        TaskList testList2 = new TaskList(listInput);

        //Testing printing of empty TaskList
        assertEquals(testList1.printOut(), "");
        //Testing printing of TaskList with 1 task
        assertEquals(testList2.printOut(), "1. [T][ ] a \n");
        //Testing marking of TaskList
        testList2.mark(1);
        assertEquals(testList2.printOut(), "1. [T][X] a \n");
        //Testing un-marking of TaskList
        testList2.unmark(1);
        assertEquals(testList2.printOut(), "1. [T][ ] a \n");
        //Testing of adding a task to TaskList
        testList1.add(new ToDo("a"));
        assertEquals(testList1.printOut(), testList2.printOut());
        //Testing of getting TaskList size
        assertEquals(testList1.size(), 1);
        //Testing of clearing TaskList
        testList1.clear();
        assertEquals(testList1.size(), 0);
        //Testing of deleting item
        testList2.delete(1);
        assertEquals(testList2.printOut(), testList1.printOut());
        //Testing of adding tag
        testList1.add(new ToDo("a"));
        testList1.addTag(1, "b");
        assertEquals(testList1.printOut(), "1. [T][ ] a #b \n");
    }
}

