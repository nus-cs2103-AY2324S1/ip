import org.junit.jupiter.api.Test;
import duke.task.ToDo;
import duke.task.TaskArray;
import duke.task.Task;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void Test(){
        Task taskA = new ToDo("TaskA");
        Task taskB = new ToDo("TaskB");
        Task taskC = new ToDo("TaskC");
        TaskArray taskArray = new TaskArray();

        taskArray.add(taskA);
        taskArray.add(taskB);
        taskArray.add(taskC);

        taskArray.get(2).mark();
        assertEquals(taskArray.get(2).getChecked(), "[/]");
        taskArray.get(2).unmark();
        assertEquals(taskArray.get(2).getChecked(), "[]");

        taskArray.get(2).mark();
        assertEquals(taskArray.get(2).getChecked(), "[/]");
        taskArray.printTaskArrayList();
    }

    @Test
    public void removingTest(){
        Task taskA = new ToDo("TaskA");
        Task taskB = new ToDo("TaskB");
        Task taskC = new ToDo("TaskC");
        TaskArray taskArray = new TaskArray();

        taskArray.add(taskA);
        taskArray.add(taskB);
        taskArray.add(taskC);

        taskArray.removeTask(1);

        //Check Whether did B get removed
        assertEquals(taskArray.get(0), taskA);
        assertEquals(taskArray.get(1), taskC);
        taskArray.printTaskArrayList();
    }
}

