package noac.task;

import noac.Parser;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class DeadlineTest {

    @Test
    public void testPrintToFile() {

        Deadline d = new Deadline("hello", LocalDateTime.parse("2001-10-10 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));

        String result = "D|0|hello|2001-10-10 1800";

        assertEquals(result ,d.printToFile());

        Deadline d2 = new Deadline("hello", LocalDateTime.parse("2001-10-10 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));

        String result2 = "D|1|hello|2001-10-10 1800";

        d2.markAsDone();

        assertEquals(result2 ,d2.printToFile());

        Deadline d3 = new Deadline("hello", Parser.parseDate("2001-10-10"));

        String result3 = "D|0|hello|2001-10-10 0000";


        assertEquals(result3 ,d3.printToFile());

    }

}