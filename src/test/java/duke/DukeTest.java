package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
  @Test
  public void createValidTodo_addSuccessfully() {
    Duke duke = new Duke("/testData/duke.txt");
    assertAll(() -> duke.tasks.addTask(new Todo("Sample task name")));
  }

  @Test
  public void invalidTodo_exceptionThrown() {
    Duke duke = new Duke("/testData/duke.txt");
    assertThrows(DukeException.class, () -> duke.tasks.addTask(new Todo("")));
  }

  @Test
  public void createValidDate_addSuccessfully() {
    Duke duke = new Duke("/testData/duke.txt");
    assertAll(() -> duke.tasks.addTask(new Deadline("Sample task name", "23-09-2024 13:00")));
  }

  @Test
  public void invalidDate_exceptionThrown() {
    Duke duke = new Duke("/testData/duke.txt");
    assertThrows(DateTimeParseException.class,
        () -> duke.tasks.addTask(new Deadline("Sample task name", "3-09-2024 13:00")));
  }
}
