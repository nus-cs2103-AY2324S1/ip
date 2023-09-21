package duke.util;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.exception.DeadlineException;

public class ParserTest {
    @Test
    public void parseLoadDeadlineTest_standardInput() {
        String input = "D | 0 | return book | 2023-09-11T13:00";
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2023-09-11T13:00"));
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
