package cyrus.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
  private static final Parser parser = new Parser();

  @Test
  public void testParseWithEmptyLine() {
    Assertions.assertEquals(parser.parse(""), ParseInfo.EMPTY);
    Assertions.assertEquals(parser.parse("  "), ParseInfo.EMPTY);
  }
}
