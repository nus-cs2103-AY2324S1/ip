package bot.parsers;

import bot.exception.BotException;
import bot.exception.DateTimeParseBotException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DatetimeParserTest {

    @Test
    public void parseTimeInput_wrongFormat_exceptionThrown() {
        try {
            DatetimeParser.parseTimeInput("18:20 1/12/2019");
            fail();
        } catch (DateTimeParseBotException e) {
            assertEquals("Please write your dateTime as d/MM/yyyy HH:mm",
                    e.getMessage());
        }
    }
}
