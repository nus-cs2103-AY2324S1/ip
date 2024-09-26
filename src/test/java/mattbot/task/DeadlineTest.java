package mattbot.task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    private LocalDateTime lDT1 = LocalDateTime.of(2023, 9, 13, 22, 34);

    @Test
    public void toFile_workingInput_success() {
        assertEquals(new Deadline("Test", lDT1).toFile(), "D | 0 | Test | 20230913T2234");
    }

    @Test
    public void print_workingInput_success() {
        assertEquals(new Deadline("Test", lDT1).toString(), "[D] [ ] Test (by: Sept 13 2023 22:34)");
    }
}
