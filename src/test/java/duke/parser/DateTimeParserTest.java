package duke.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class DateTimeParserTest {
    @Test
    public void outFormattedDateTIme_shouldHaveCorrectFormat() throws KoraException {
        DateTimeParser dtParser = new DateTimeParser();
        String expected = "Sat, Aug 5 2023 12:11";
        String actual = dtParser.outFormatted("2023-08-05 12:11");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void saveFormattedDateTIme_shouldHaveCorrectFormat() throws KoraException {
        DateTimeParser dtParser = new DateTimeParser();
        String expected = "2023-08-05 12:11";
        String actual = dtParser.saveFormatted("2023-08-05 12:11");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void wronglyFormattedString_forOutFormatted_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    DateTimeParser dtParser = new DateTimeParser();
                    dtParser.outFormatted("2023-08-05");
                });
        String expected = "Omo! The date format should be yyyy-MM-dd HH-mm!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    public void wronglyFormattedString_forSaveFormatted_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    DateTimeParser dtParser = new DateTimeParser();
                    dtParser.saveFormatted("2023-08-05");
                });
        String expected = "Omo! The date format should be yyyy-MM-dd HH-mm!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    public void stringWithWhiteSpaces_forOutFormatted_shouldHaveCorrectFormat() throws KoraException {
        DateTimeParser dtParser = new DateTimeParser();
        String expected = "Sat, Aug 5 2023 12:11";
        String actual = dtParser.outFormatted("          2023-08-05         12:11    ");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringWithWhiteSpaces_forSavedFormatted_shouldHaveCorrectFormat() throws KoraException {
        DateTimeParser dtParser = new DateTimeParser();
        String expected = "2023-08-05 12:11";
        String actual = dtParser.saveFormatted("  2023-08-05       12:11  ");
        Assertions.assertEquals(expected, actual);
    }
}
