package joe;

import joe.tasks.DeadlineTask;
import joe.tasks.EventTask;
import joe.tasks.Task;
import joe.tasks.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
  @Test
  public void testEmptyString() {
    assertEquals("Here are your tasks:", new TaskList().toString());
  }

  @Test
  public void testUnmarkedString() {
    LocalDateTime dt =
        LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    TaskList tasks = new TaskList();
    tasks.add(new TodoTask("Todo"));
    tasks.add(new DeadlineTask("Deadline", dt));
    tasks.add(new EventTask("Event", dt, dt));
    assertEquals(
        "Here are your tasks:\n"
            + "1.[T][ ] Todo\n"
            + "2.[D][ ] Deadline (by: 01 Jan 2000 00:00)\n"
            + "3.[E][ ] Event (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)",
        tasks.toString());
  }

  @Test
  public void testMarkedString() {
    LocalDateTime dt =
        LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    TaskList tasks = new TaskList();
    TodoTask newTodo = new TodoTask("Todo");
    DeadlineTask newDeadline = (new DeadlineTask("Deadline", dt));
    EventTask newEvent = (new EventTask("Event", dt, dt));
    tasks.add(newTodo);
    tasks.add(newDeadline);
    tasks.add(newEvent);
    newTodo.markAsDone();
    newDeadline.markAsDone();
    newEvent.markAsDone();
    assertEquals(
        "Here are your tasks:\n"
            + "1.[T][X] Todo\n"
            + "2.[D][X] Deadline (by: 01 Jan 2000 00:00)\n"
            + "3.[E][X] Event (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)",
        tasks.toString());
  }
}
