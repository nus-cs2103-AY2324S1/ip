package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests Parser class.
 */
public class ParserTest {

    /**
     * Tests parse function with command bye.
     */
    @Test
    public void parse_bye() {
        Parser p = new Parser("bye");
        String farewellMessage = Ui.farewellMessage();
        assertEquals(farewellMessage, p.parse());
    }
}
