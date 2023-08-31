package Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Exceptions.DukeException;

public class TaskListTest {
  
  @Test
  public void Test1() {
    TaskList tasklist = new TaskList();
    tasklist.add("hi", true);
    try {
      tasklist.delete(0);
    } catch (DukeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(tasklist.getSize(), new TaskList().getSize());
  }

  @Test
  public void Test2() {
    TaskList tasklist = new TaskList();
    tasklist.add("hi", true);
    assertEquals(tasklist.toString(), "1.[T][X] hi\nYou have 1 task(s) in the list.");
  }
}
