package duke.data.task.tasklist;

import duke.data.task.Task;
import duke.data.task.builder.TaskBuilder;
import org.junit.jupiter.api.Test;

public class TasklistTest {
    Tasklist tasklist = new Tasklist();
    TaskBuilder taskBuilder = new TaskBuilder();

    @Test
    public void getTaskRepresentationTest() {

       try {
           Task task1 = taskBuilder.buildFromString("todo read book");
           Task task2 = taskBuilder.buildFromString("deadline return book /by 2020-02-02");
           Task task3 = taskBuilder.buildFromString("event project meeting /from 2020-02-02 12:00 /to 2020-02-02 13:00");
           tasklist.addTask(task1);
           tasklist.addTask(task2);
           tasklist.addTask(task3);
       } catch (Exception e) {
           assert false : "should not reach here";
           System.out.println(e.getMessage());
       }
       String tasks = tasklist.getTaskRepresentations();
       assert true;

    }

}
