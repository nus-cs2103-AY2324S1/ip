package dude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dude.exception.DudeException;
import dude.exception.InvalidDateTimeArgumentException;

public class ParserTest {
  private static final String EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG = "Invalid date/time format. "
  + "Check if the date and time is\n"
    + "in the format: \"DD/MM/YYYY hhmm\"\n"
    + "e.g. 31/12/2023 2359";

  @Test
  public void parseDateTime_validInput_success() {
    try {
      // valid input (leading zeroes)
      assertEquals(LocalDateTime.of(2023, 9, 6, 18, 0),
        Parser.parseDateTime("06/09/2023 1800"));

      // valid input (no leading zeroes)
      assertEquals(LocalDateTime.of(2024, 3, 20, 0, 0),
        Parser.parseDateTime("20/3/2024 0000"));

      // valid input (same as above, leap year)
      Parser.parseDateTime("29/2/2024 0000");
    } catch (DudeException e) {
      fail();
    }
  }

  @Test
  public void parseDateTime_invalidInputFormat_exceptionThrown() {
    final String testFailMsg = "InvalidDateTimeArgumentException error was expected "
    + "for inputs with invalid format.";

    // invalid input (extra/wrong characters)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("monday"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024  0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20-3-2024 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024 00:00"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024, 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024 0000h"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("2024/3/20 0000"), testFailMsg).getMessage());

    // invalid input (missing date)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("3/2024 0000"), testFailMsg).getMessage());

    // invalid input (missing year)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3 0000"), testFailMsg).getMessage());

    // invalid input (missing time)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024"), testFailMsg).getMessage());
  }

  @Test
  public void parseDateTime_inputOutOfBounds_exceptionThrown() {
    final String testFailMsg = "InvalidDateTimeArgumentException error was expected "
    + "for inputs with dates/times out of bounds.";

    // invalid input (month out of bounds)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/-1/2024 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/0/2024 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/13/2024 0000"), testFailMsg).getMessage());

    // invalid input (day out of bounds)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("0/3/2024 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("-1/3/2024 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("32/3/2024 0000"), testFailMsg).getMessage());

    // invalid input (time out of bounds)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024 2400"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024 2360"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("20/3/2024 0099"), testFailMsg).getMessage());
  }

  @Test
  public void parseDateTime_nonexistentDateTimes_exceptionThrown() {
    String testFailMsg = "InvalidDateTimeArgumentException error was expected "
      + "for inputs with nonexistent dates/times.";

    // invalid input (date does not exist)
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("30/2/2024 0000"), testFailMsg).getMessage());
    assertEquals(EXPECTED_INVALID_DATE_TIME_EXCEPTION_MSG,
      assertThrows(InvalidDateTimeArgumentException.class, () ->
        Parser.parseDateTime("29/2/2023 0000"), testFailMsg).getMessage());
  }
}
