package noac;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class ParserTest {

    @Test
    public void testParseDate() {

        LocalDateTime result = LocalDateTime.parse("2001-10-10 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals(result.toString(), Parser.parseDate("2001-10-10").toString());

        LocalDateTime result2 = LocalDateTime.parse("2001-10-10 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals(result2.toString(), Parser.parseDate("2001-10-10 1800").toString());

        DateTimeParseException e = assertThrows(DateTimeParseException.class, () -> Parser.parseDate("2001-10-101800"));
    }

}
