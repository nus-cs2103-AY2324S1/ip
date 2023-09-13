package horo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import horo.data.tasks.Deadline;

public class ParserTest {

  @Test
  public void parse_Deadline_parsedCorrectly() throws Exception {
    final String input = "D,1,return book,2019/10/15 13:10";

    assertEquals(Parser.parseTaskDataString(input).getClass(), Deadline.class);
  }
}
