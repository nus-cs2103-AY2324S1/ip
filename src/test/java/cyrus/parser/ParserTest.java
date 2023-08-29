package cyrus.parser;

import cyrus.commands.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
  private static final Parser parser = new Parser();

  @Test
  public void testParseWithEmptyLine() {
    assertEquals(parser.parse(""), ParseInfo.EMPTY);
    assertEquals(parser.parse("  "), ParseInfo.EMPTY);
  }

  @Test
  public void testParseWithUnknownCommand() {
    assertEquals(parser.parse("unknown hi this is unknown").commandType, CommandType.UNKNOWN);
  }

  @Test
  public void testParseWithNoArgument() {
    assertEquals(
        parser.parse("todo /option1 this /other other option!").getArgument(),
        ""
    );
  }
}
