package io;

import exceptions.ParserException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

  @Test
  public void parseEvent_noByStatement_exceptionThrown() {

    String input = "deadline hello test\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Parser parser = new Parser();
    try {
      parser.update();
    } catch (NoSuchElementException ex) {
      System.out.println("Should not reach here");
    }

    try {
      parser.parseEvent();
      fail();
    } catch (ParserException ex) {
      System.out.println(ex.getMessage());
    }
    System.setIn(System.in);
  }
}
