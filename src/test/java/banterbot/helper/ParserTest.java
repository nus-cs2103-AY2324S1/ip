package banterbot.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
public class ParserTest {
    @Test
    public void parseIndexCommand_withNoIndex_missingIndexExceptionThrown() {
        try {
            Parser.parse("mark ");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("mark needs an index after it...", e.getMessage());
        }
    }

    @Test
    public void parseIndexlessCommand_withExtraArgument_wrongUseOfCommandExceptionThrown() {
        try {
            Parser.parse("list this");
        } catch (Exception e) {
            assertEquals("No extra text after this BanterBot.command bro..", e.getMessage());
        }
    }

    @Test
    public void parseInvalidCommand_notInCommandList_invalidCommandExceptionThrown() {
        String command = "destroy";
        try {
            Parser.parse(command);
        } catch (Exception e) {
            assertEquals("No such BanterBot.command bruh: " + command, e.getMessage());
        }
    }
}
