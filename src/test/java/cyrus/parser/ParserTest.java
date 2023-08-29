package cyrus.parser;

import cyrus.commands.CommandType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
  private static final Parser parser = new Parser();

  @Test
  public void testParseWithEmptyLine() {
    assertEquals(ParseInfo.EMPTY, parser.parse(""));
    assertEquals(ParseInfo.EMPTY, parser.parse("  "));
  }

  @Test
  public void testParseWithUnknownCommand() {
    assertEquals(CommandType.UNKNOWN, parser.parse("unknown hi this is unknown").commandType);
  }

  @Test
  public void testParseWithNoArgument() {
    assertEquals(
        "",
        parser.parse("todo /option1 this /other other option!").getArgument()
    );
  }

  @Test
  public void testParseWithOptions() {
    HashMap<String, String> options =
        parser.parse("todo /a this is a /b b /c hello! / this should be together").getOptions();
    HashMap<String, String> expected = new HashMap<>();
    expected.put("a", "this is a");
    expected.put("b", "b");
    expected.put("c", "hello! / this should be together");
    for (var entry : expected.entrySet()) {
      assertTrue(options.containsKey(entry.getKey()));
      assertEquals(entry.getValue(), options.getOrDefault(entry.getKey(), "invalid"));
    }
  }
}
