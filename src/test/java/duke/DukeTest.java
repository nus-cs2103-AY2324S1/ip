package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private Duke duke = new Duke();

    /**
     * Tests the getResponse() function with an invalid input.
     */
    @Test
    public void testGetResponseWithInvalidInput() {
        String response = duke.getResponse("invalidCommand");
        assertEquals(" OOPS!!! I'm sorry, but I don't know what that means :-(", response);
    }
}
