package horo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import horo.HoroException;

public class TaskListTest {
  private Task todoTask;
  private Task deadlineTask;
  private Task eventTask;

  private TaskList taskList;

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() throws HoroException {
    todoTask = new Todo("Todo Description");
    deadlineTask = new Deadline("Deadline Description", "2023/01/01 13:00");
    eventTask = new Event("Event Description", "2023/01/01 13:00", "2023/01/01 15:00");

    ArrayList<Task> t = new ArrayList<>();
    t.add(todoTask);
    t.add(deadlineTask);
    t.add(eventTask);

    taskList = new TaskList(t);

    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  public void removeTask_taskExists_removesNormally() throws Exception {
    int numberOfTasksBeforeRemoval = taskList.getTasks().size();
    taskList.removeTask(0);

    assertFalse(taskList.getTasks().contains(todoTask));

    int numberOfTasksAfterRemoval = taskList.getTasks().size();
    assertTrue(numberOfTasksAfterRemoval == numberOfTasksBeforeRemoval - 1);
  }

  @Test
  public void markTaskDone_taskExists_markNormally() throws Exception {
    taskList.markTaskDone(0);

    assertTrue(todoTask.isDone());
  }

  @Test
  public void markTaskDone_taskNotExists_throwMessage() throws Exception {
    taskList.markTaskDone(10);

    assertEquals(
        "Please enter a valid number from 1 - " + taskList.getTasks().size(),
        outputStreamCaptor.toString().trim());
  }
}
