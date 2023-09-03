package io;

import exceptions.ParserException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import tasks.Deadline;

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

}
