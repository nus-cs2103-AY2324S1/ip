package linus.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import linus.exception.LinusException;

public class TaskListTest {
    @Test
    public void add_addDifferentNewTasks_newTasksSuccesfullyAddedInOrder() {
        TaskList tasks = new TaskList();
        Task toDo, deadline, event;
        try {
            toDo = new ToDo("task 1");
            tasks.add(toDo);

            deadline = new Deadline("task 2", "2023-08-31");
            tasks.add(deadline);

            event = new Event("task 3", "2023-08-31", "2023-09-01");
            tasks.add(event);

            // length of TaskList is 3
            List<Task> listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());

            // the tasks are at their respective indices according to insertion order
            assertEquals(toDo, listOfTasks.get(0));
            assertEquals(deadline, listOfTasks.get(1));
            assertEquals(event, listOfTasks.get(2));

        } catch (LinusException e) {
            fail("LinusException thrown while executing the test case");
        }
    }

}
