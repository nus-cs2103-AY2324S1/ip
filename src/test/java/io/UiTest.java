package io;

import exceptions.ParserException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class UiTest {

  @Test
  public void displayTask_Deadlineobj_output() {
    LocalDate date = LocalDate.of(2021, 1, 1);
    String name = "hello ";
    Deadline input = new Deadline(name, date);
    Ui ui = new Ui();
    String output = ui.displayTask(input);
    assertEquals("[D][ ] hello (2021-01-01)", output);
  }

  @Test
  public void displayTask_Eventobj_output() {
    String name = "hello ";
    String start = "mon";
    String end = "sun";
    Ui ui = new Ui();
    Event input = new Event(name, start, end);
    String output = ui.displayTask(input);
    assertEquals("[E][ ] hello (mon to sun)", output);
  }

  @Test
  public void displayTask_todoobj_output() {
    String name = "hello ";
    Ui ui = new Ui();
    Todo input = new Todo(name);
    String output = ui.displayTask(input);
    assertEquals("[T][ ] hello ", output);
  }

}
