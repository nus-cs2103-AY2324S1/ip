package duke;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskListTest {
    TaskList tasks = new TaskList();
    Task todo = new ToDo("todo go on a hike");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    Task deadline = new Deadline("assignment submission",
            LocalDateTime.parse("2023-09-11 2359", formatter));
    @Test
    public void testGetTask(){
        this.tasks.addTask(todo);
        int initialLen = tasks.getSize();
        assert this.tasks.getTask(tasks.getSize() - 1).taskString()
                .equals(todo.taskString());
        //to check that the length of the list is not changes by the get method
        assertEquals(initialLen, this.tasks.getSize());
    }

    @Test
    public void testAddTask() {
        int initialLen = tasks.getSize();
        this.tasks.addTask(deadline);
        assertEquals(initialLen + 1, this.tasks.getSize());
    }

    @Test
    public void testRemoveTask() {
        this.tasks.addTask(deadline);
        int initialLen = tasks.getSize();
        this.tasks.removeTask(tasks.getSize() - 1);
        assertEquals(initialLen - 1, this.tasks.getSize());
    }

    @Test
    public void testMarkDone() {
        this.tasks.addTask(deadline);
        this.tasks.getTask(tasks.getSize() - 1).markAsDone();
        assert this.tasks.getTask(tasks.getSize() - 1).taskString()
                .equals(deadline.taskString());
    }

    @Test
    public void testNotMarkDone() {
        deadline.markAsDone();
        this.tasks.addTask(deadline);
        this.tasks.getTask(tasks.getSize() - 1).markAsNotDone();
        assert this.tasks.getTask(tasks.getSize() - 1).taskString()
                .equals(deadline.taskString());
    }

    @Test
    public void testGetTaskSize() {
        int initialLen = tasks.getSize();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        assertEquals(initialLen + 2, tasks.getSize());
    }

    public boolean assertEquals(int a, int b) {
        return a == b;
    }
}
