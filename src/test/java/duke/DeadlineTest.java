package duke; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


/**
 * Class for testing the Deadline class
 */
public class DeadlineTest {
    @Test
    public void outputTest() {
        assertEquals("[D][ ] study for CS2103T (by: 22/06/2022 1600)",
                new Deadline("study for CS2103T", LocalDateTime.parse("2022-06-22T16:00")).toString());
    }

    @Test
    public void saveStringTest() {
        assertEquals("D | 0 | study for CS2103T | 2022-06-22T16:00 | 2",
                new Deadline("study for CS2103T",
                        LocalDateTime.parse("2022-06-22T16:00")).toSaveString());
    }

}
