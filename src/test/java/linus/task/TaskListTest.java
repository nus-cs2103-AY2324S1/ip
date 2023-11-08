package linus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import linus.exception.LinusException;

public class TaskListTest {
    @Test
    public void add_addDifferentNewTasks_newTasksSuccesfullyAddedInOrder() {
        TaskList tasks = new TaskList();
        Task toDo = null;
        Task deadline = null;
        Task event = null;
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

    @Test
    public void delete_deleteSomeTasks_tasksSuccesfullyDeleted() {
        TaskList tasks = new TaskList();
        Task toDo = null;
        Task deadline = null;
        Task event = null;
        try {
            toDo = new ToDo("task 1");
            tasks.add(toDo);

            deadline = new Deadline("task 2", "2023-08-31");
            tasks.add(deadline);

            event = new Event("task 3", "2023-08-31", "2023-09-01");
            tasks.add(event);

            List<Task> listOfTasks = null;

            // delete the first task
            tasks.delete(0);
            listOfTasks = tasks.getList();
            assertEquals(2, listOfTasks.size());
            assertEquals(deadline, listOfTasks.get(0));
            assertEquals(event, listOfTasks.get(1));

            // delete the last task
            tasks.delete(1);
            listOfTasks = tasks.getList();
            assertEquals(1, listOfTasks.size());
            assertEquals(deadline, listOfTasks.get(0));

            // delete the only task
            tasks.delete(0);
            listOfTasks = tasks.getList();
            assertEquals(0, listOfTasks.size());

        } catch (LinusException e) {
            fail("LinusException thrown while executing the test case");
        }
    }

    @Test
    public void mark_markSomeTasks_tasksSuccessfullyMarked() {
        TaskList tasks = new TaskList();
        Task toDo = null;
        Task deadline = null;
        Task event = null;
        try {
            toDo = new ToDo("task 1");
            tasks.add(toDo);

            deadline = new Deadline("task 2", "2023-08-31");
            tasks.add(deadline);

            event = new Event("task 3", "2021-08-04", "2023-09-01");
            tasks.add(event);

            List<Task> listOfTasks = null;

            // mark the first task
            tasks.mark(0);
            listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());
            assertEquals(true, listOfTasks.get(0).isDone());
            assertEquals(false, listOfTasks.get(1).isDone());
            assertEquals(false, listOfTasks.get(2).isDone());

            // mark the last task
            tasks.mark(2);
            listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());
            assertEquals(true, listOfTasks.get(0).isDone());
            assertEquals(false, listOfTasks.get(1).isDone());
            assertEquals(true, listOfTasks.get(2).isDone());

            // mark the middle task
            tasks.mark(1);
            listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());
            assertEquals(true, listOfTasks.get(0).isDone());
            assertEquals(true, listOfTasks.get(1).isDone());
            assertEquals(true, listOfTasks.get(2).isDone());

        } catch (LinusException e) {
            fail("LinusException thrown while executing the test case");
        }
    }

    @Test
    public void unmark_unmarkSomeTasks_tasksSuccessfullyUnmarked() {
        TaskList tasks = new TaskList();
        Task toDo = null;
        Task deadline = null;
        Task event = null;
        try {
            toDo = new ToDo("task 1");
            tasks.add(toDo);

            deadline = new Deadline("task 2", "2023-08-31");
            tasks.add(deadline);

            event = new Event("task 3", "2021-08-04", "2023-07-01");
            tasks.add(event);

            List<Task> listOfTasks = null;

            // mark all tasks
            tasks.mark(0);
            tasks.mark(1);
            tasks.mark(2);

            // unmark the first task
            tasks.unmark(0);
            listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());
            assertEquals(false, listOfTasks.get(0).isDone());
            assertEquals(true, listOfTasks.get(1).isDone());
            assertEquals(true, listOfTasks.get(2).isDone());

            // unmark the last task
            tasks.unmark(2);
            listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());
            assertEquals(false, listOfTasks.get(0).isDone());
            assertEquals(true, listOfTasks.get(1).isDone());
            assertEquals(false, listOfTasks.get(2).isDone());

            // unmark the middle task
            tasks.unmark(1);
            listOfTasks = tasks.getList();
            assertEquals(3, listOfTasks.size());
            assertEquals(false, listOfTasks.get(0).isDone());
            assertEquals(false, listOfTasks.get(1).isDone());
            assertEquals(false, listOfTasks.get(2).isDone());

        } catch (LinusException e) {
            fail("LinusException thrown while executing the test case");
        }
    }

    @Test
    public void find_findSomeTasks_succesfullyFoundTasks() {


        TaskList tasks = new TaskList();
        Task toDo = null;
        Task deadline = null;
        Task event = null;
        try {
            toDo = new ToDo("task 1");
            tasks.add(toDo);

            deadline = new Deadline("task 2", "2023-08-31");
            tasks.add(deadline);

            event = new Event("task 3", "2021-08-04", "2023-07-01");
            tasks.add(event);

            List<Task> listOfTasks = null;

            // find all tasks
            listOfTasks = tasks.find("task");
            assertEquals(3, listOfTasks.size());
            assertEquals(toDo, listOfTasks.get(0));
            assertEquals(deadline, listOfTasks.get(1));
            assertEquals(event, listOfTasks.get(2));

            // find some tasks
            listOfTasks = tasks.find("task 2");
            assertEquals(1, listOfTasks.size());
            assertEquals(deadline, listOfTasks.get(0));

            // find no tasks
            listOfTasks = tasks.find("task 4");
            assertEquals(0, listOfTasks.size());

        } catch (LinusException e) {
            fail("LinusException thrown while executing the test case");
        }
    }

}
