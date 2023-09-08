package dude.task;

import dude.exception.DudeException;
import dude.exception.InvalidTaskIndexException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
  @Test
  public void remove_validIndex_success() {
    Task task1 = new Task("1");
    Task task2 = new Task("2");
    Task task3 = new Task("3");
    Task task4 = new Task("4");
    ArrayList<Task> tasks = new ArrayList<>();
    tasks.add(task1);
    tasks.add(task2);
    tasks.add(task3);
    tasks.add(task4);
    TaskList taskList = new TaskList(tasks);

    try {
      assertEquals(task2, taskList.remove(2));
      assertEquals(task1, taskList.remove(1));
      assertEquals(task4, taskList.remove(2));
    } catch (DudeException e) {
      fail();
    }
  }

  @Test
  public void remove_indexOutOfBounds_exceptionThrown() {
    final String testFailMsg = "InvalidTaskIndexException error was expected "
      + "for inputs with out-of-bounds index.";

    Task task1 = new Task("1");
    Task task2 = new Task("2");
    Task task3 = new Task("3");
    Task task4 = new Task("4");
    ArrayList<Task> tasks = new ArrayList<>();
    tasks.add(task1);
    tasks.add(task2);
    tasks.add(task3);
    tasks.add(task4);
    TaskList taskList = new TaskList(tasks);

    assertEquals("I can't find the task numbered \"-1\".\nTry checking if you've typed the correct task number.",
      assertThrows(InvalidTaskIndexException.class, () ->
        taskList.remove(-1), testFailMsg).getMessage());
    assertEquals("I can't find the task numbered \"0\".\nTry checking if you've typed the correct task number.",
      assertThrows(InvalidTaskIndexException.class, () ->
        taskList.remove(0), testFailMsg).getMessage());
    assertEquals("I can't find the task numbered \"5\".\nTry checking if you've typed the correct task number.",
      assertThrows(InvalidTaskIndexException.class, () ->
        taskList.remove(5), testFailMsg).getMessage());
  }

  @Test
  public void getTask_validIndex_success() {
    Task task1 = new Task("1");
    Task task2 = new Task("2");
    Task task3 = new Task("3");
    Task task4 = new Task("4");
    ArrayList<Task> tasks = new ArrayList<>();
    tasks.add(task1);
    tasks.add(task2);
    tasks.add(task3);
    tasks.add(task4);
    TaskList taskList = new TaskList(tasks);

    try {
      assertEquals(task1, taskList.getTask(1));
      assertEquals(task2, taskList.getTask(2));
      assertEquals(task3, taskList.getTask(3));
      assertEquals(task4, taskList.getTask(4));
    } catch (DudeException e) {
      fail();
    }
  }

  @Test
  public void getTask_invalidIndex_exceptionThrown() {
    final String testFailMsg = "InvalidTaskIndexException error was expected "
      + "for inputs with out-of-bounds index.";

    Task task1 = new Task("1");
    Task task2 = new Task("2");
    Task task3 = new Task("3");
    Task task4 = new Task("4");
    ArrayList<Task> tasks = new ArrayList<>();
    tasks.add(task1);
    tasks.add(task2);
    tasks.add(task3);
    tasks.add(task4);
    TaskList taskList = new TaskList(tasks);

    assertEquals("I can't find the task numbered \"-1\".\nTry checking if you've typed the correct task number.",
      assertThrows(InvalidTaskIndexException.class, () ->
        taskList.getTask(-1), testFailMsg).getMessage());
    assertEquals("I can't find the task numbered \"0\".\nTry checking if you've typed the correct task number.",
      assertThrows(InvalidTaskIndexException.class, () ->
        taskList.getTask(0), testFailMsg).getMessage());
    assertEquals("I can't find the task numbered \"5\".\nTry checking if you've typed the correct task number.",
      assertThrows(InvalidTaskIndexException.class, () ->
        taskList.getTask(5), testFailMsg).getMessage());
  }
}
