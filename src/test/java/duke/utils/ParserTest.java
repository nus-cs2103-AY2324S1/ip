package duke.utils;

import org.junit.jupiter.api.Test;

import duke.commands.DeadlineCommand;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;

public class ParserTest {

    @Test
    public void test1() {
        try {
            assertEquals(Parser.parseDeadline("blah /by 12/12/2012 16:00", false),
                    new DeadlineCommand(null, false, LocalDate.of(2012, 12, 12), LocalTime.of(16, 0)));
        } catch (IncorrectFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            assertEquals(Parser.map("delete"), DukeEnum.DELETE);
        } catch (DukeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
