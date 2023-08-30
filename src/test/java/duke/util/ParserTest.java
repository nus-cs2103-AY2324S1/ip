package duke.util;

import duke.task.Deadline;
import duke.task.DeadlineException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseLoadDeadlineTest_standardInput() {
        String input = "D | 0 | return book | 2023-09-11";
        Deadline deadline = new Deadline("return book", LocalDate.parse("2023-09-11"));
        Assertions.assertEquals(Parser.parseLoadDeadline(input).toString(), deadline.toString());
    }

    @Test
    public void parseUserDeadlineTest_missingByDate() {
        String input = "deadline return book";
        try {
            Parser.parseUserDeadline(input);
            fail();
        } catch (DeadlineException e) {
            Assertions.assertEquals(e.getMessage(), new DeadlineException().getMessage());
        }
    }
}
