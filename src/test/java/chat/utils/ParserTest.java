package chat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import chat.commands.DeadlineCommand;
import chat.exceptions.ChatException;
import chat.exceptions.IncorrectFormatException;

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
            assertEquals(Parser.map("delete"), Enum.DELETE);
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
