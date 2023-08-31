package cheese.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
  @Test
  public void testIsCommand() {
    Parser parser = new Parser();
    assertEquals(true, parser.isCommand("list"));
    assertEquals(false, parser.isCommand("done"));
    assertEquals(true, parser.isCommand("todo"));
  }

}
