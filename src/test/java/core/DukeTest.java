package core;

import core.Duke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

  private final InputStream sysInBackup = System.in;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void testListCommand() {
    ByteArrayInputStream in = new ByteArrayInputStream("list".getBytes());
    System.setIn(in);
    new Duke("data/tasks.txt").run();

    String expectedOutput = "Here are the tasks in your list:\n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void testTodoCommand() {
    ByteArrayInputStream in = new ByteArrayInputStream("todo hi".getBytes());
    System.setIn(in);
    new Duke("data/tasks.txt").run();

    String expectedOutput = "Got it. I've added this task:\n" +
      "[T][ ] hi\n" +
      "Now you have 1 tasks in the list.\n";
    assertEquals(expectedOutput, outContent.toString());
  }
}
