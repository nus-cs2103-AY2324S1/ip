package cringebot.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CringeBotExceptionTest {
    @Test
    public void toStringTest1() {
        CringeBotException newException = new CringeBotException(
                "OOPS!!! I'm sorry, but I don't know what that means. :(( "
        );
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means. :(( ", newException.getMessage());
    }
}
