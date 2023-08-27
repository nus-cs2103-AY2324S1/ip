package duke;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
  @Test
  public void markTask_validIndex_taskMarkCompleted() throws WrongIndexException {
    TaskList taskList = new TaskList(new LinkedList<>());
    Task t = new ToDoTask("name1");
    taskList.addTask(t);
    taskList.markTask("1");
    assertTrue(t.isCompleted());
  }

  @Test
  public void markTask_leftOutOfBounds_exceptionThrown() throws WrongIndexException {
    WrongIndexException e = assertThrows(WrongIndexException.class, () -> {
      TaskList taskList = new TaskList(new LinkedList<>());
      Task t = new ToDoTask("name1");
      taskList.addTask(t);
      taskList.markTask("0");
    });

    assertEquals( "    Enter a valid index", e.getMessage());
  }

  @Test
  public void markTask_rightOutOfBounds_exceptionThrown() throws WrongIndexException {
    WrongIndexException e = assertThrows(WrongIndexException.class, () -> {
      TaskList taskList = new TaskList(new LinkedList<>());
      Task t = new ToDoTask("name1");
      taskList.addTask(t);
      taskList.markTask("2");
    });

    assertEquals( "    Enter a valid index", e.getMessage());
  }

  @Test
  public void markTask_largeInteger_exceptionThrown() throws WrongIndexException {
    WrongIndexException e = assertThrows(WrongIndexException.class, () -> {
      TaskList taskList = new TaskList(new LinkedList<>());
      taskList.markTask("2000000000000000000000000000000000000");
    });

    assertEquals( "    Enter a valid index", e.getMessage());
  }

  @Test
  void findTask_emptyString_false() {
    Task t = new ToDoTask("name1 name2");
    LinkedList<Task> lists = new LinkedList<>();
    lists.add(t);
    TaskList taskList = new TaskList(lists);
    assertFalse(taskList.findTasks(""));
  }

  @Test
  void findTask_validString_true() {
    Task t = new ToDoTask("name1 name2");
    LinkedList<Task> lists = new LinkedList<>();
    lists.add(t);
    TaskList taskList = new TaskList(lists);
    assertTrue(taskList.findTasks("name1 "));
  }
}
