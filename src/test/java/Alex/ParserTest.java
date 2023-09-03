package Alex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void parseTest() {
        String userInput = "2023-SEP-02";
        Command c = Parser.parse(userInput);
        assertEquals(true, c instanceof UnknownCommand);
    }
}
