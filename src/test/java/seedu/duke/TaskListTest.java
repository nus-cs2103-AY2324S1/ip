package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import exception.InvalidIndexException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class TaskListTest {

    @Test
    public void editTask_invalidIndex_exceptionThrown() {
        ArrayList<Task> taskLists = new ArrayList<>();
        taskLists.add(new ToDo("return book"));
        TaskList tasks = new TaskList(taskLists);

        assertThrows(InvalidIndexException.class, () -> tasks.editTask("mark", -1));

        assertThrows(InvalidIndexException.class, () -> tasks.editTask("mark", 100));
    }

    @Test
    public void testFileStringFormat() {
        ArrayList<Task> taskLists = new ArrayList<>();
        ToDo task1 = new ToDo("return book");
        task1.markTask();

        taskLists.add(task1);
        taskLists.add(new Deadline("buy supplies", "Jan 5 2023 7.30PM"));
        taskLists.add(new Event("host party", "Jan 5 2023 7.30PM", "Jan 6 2023 1.00AM"));
        TaskList tasks = new TaskList(taskLists);

        assertEquals("T | 1 | return book\n" + "D | 0 | buy supplies | Jan 5 2023 7.30PM\n"
                + "E | 0 | host party | Jan 5 2023 7.30PM-Jan 6 2023 1.00AM", tasks.toFileString());
    }

    @Test
    public void testFindTask() {
        ArrayList<Task> taskLists = new ArrayList<>();
        taskLists.add(new ToDo("return book"));
        taskLists.add(new ToDo("buy Books"));
        TaskList tasks = new TaskList(taskLists);

        assertEquals("Here are the matching tasks in your list:\n1.[T][ ] return book\n2.[T][ ] buy Books\n",
                tasks.findMatchingTasks("book"));

        assertEquals("No task matching keyword found!", tasks.findMatchingTasks("hoo"));
    }
}
