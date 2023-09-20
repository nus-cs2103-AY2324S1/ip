package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BeeExceptionTest {
    @Test
    public void beeExceptionTest() {
        BeeException beeException = new BeeException("Test");
        assertEquals("Test", beeException.getMessage());
    }
}
