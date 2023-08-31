package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart().appendPattern(" HH:mm").optionalEnd()
            .optionalStart().appendPattern(" HHmm").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    private Deadline deadline;



    @BeforeEach
    public void init() {
        this.deadline = new Deadline("return book", LocalDateTime.parse("2023-08-30 22:19", FORMATTER));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: Aug 30 2023 22:19)", this.deadline.toString());
    }

    @Test
    public void testStorageFileConversion() {
        assertEquals("D | 0 | return book | 2023-08-30T22:19", this.deadline.formatForStorage());
    }

    @Test
    public void testMarkDone() {
        this.deadline.markDone();
        assertEquals("[D][X] return book (by: Aug 30 2023 22:19)", this.deadline.toString());
    }

    @Test
    public void testWithinDateRange_success() {
        assertTrue(this.deadline.isWithinDateRange(LocalDateTime.parse("2023-08-30", FORMATTER)));
    }

    @Test
    public void testWithinDateRange_failure() {
        assertFalse(this.deadline.isWithinDateRange(LocalDateTime.parse("2023-01-01", FORMATTER)));
    }

}
