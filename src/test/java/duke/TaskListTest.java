
package duke;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.*;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

  private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

  @BeforeAll
  public static void init() {
    Platform.startup(() -> {});
  }

  @AfterAll
  public static void exit() {
    Platform.exit();
  }

  @Test
  public void markTask_validIndex_taskMarkCompleted() throws WrongIndexException {
    TaskList taskList = new TaskList(new LinkedList<>());
    VBox vbox = new VBox();
    Task t = new ToDoTask("name1");
    taskList.addTask(t, vbox, dukeImage);
    taskList.markTask("1", vbox, dukeImage);
    assertTrue(t.isCompleted());
  }

  @Test
  public void markTask_leftOutOfBounds_exceptionThrown() throws WrongIndexException {
    WrongIndexException e = assertThrows(WrongIndexException.class, () -> {
      TaskList taskList = new TaskList(new LinkedList<>());
      VBox vbox = new VBox();
      Task t = new ToDoTask("name1");
      taskList.addTask(t, vbox, dukeImage);
      taskList.markTask("0", vbox, dukeImage);
    });

    assertEquals( "    Enter a valid index", e.getMessage());
  }

  @Test
  public void markTask_rightOutOfBounds_exceptionThrown() throws WrongIndexException {
    WrongIndexException e = assertThrows(WrongIndexException.class, () -> {
      TaskList taskList = new TaskList(new LinkedList<>());
      VBox vbox = new VBox();
      Task t = new ToDoTask("name1");
      taskList.addTask(t, vbox, dukeImage);
      taskList.markTask("2", vbox, dukeImage);
    });

    assertEquals( "    Enter a valid index", e.getMessage());
  }

  @Test
  public void markTask_largeInteger_exceptionThrown() throws WrongIndexException {
    WrongIndexException e = assertThrows(WrongIndexException.class, () -> {
      TaskList taskList = new TaskList(new LinkedList<>());
      VBox vbox = new VBox();
      taskList.markTask("2000000000000000000000000000000000000", vbox, dukeImage);
    });

    assertEquals( "    Enter a valid index", e.getMessage());
  }

  @Test
  void findTask_emptyString_false() {
    Task t = new ToDoTask("name1 name2");
    LinkedList<Task> lists = new LinkedList<>();
    VBox vbox = new VBox();
    lists.add(t);
    TaskList taskList = new TaskList(lists);
    assertFalse(taskList.findTasks("", vbox, dukeImage));
  }

  @Test
  void findTask_validString_true() {
    Task t = new ToDoTask("name1 name2");
    LinkedList<Task> lists = new LinkedList<>();
    lists.add(t);
    VBox vbox = new VBox();
    TaskList taskList = new TaskList(lists);
    assertTrue(taskList.findTasks("name1 ", vbox, dukeImage));
  }
}