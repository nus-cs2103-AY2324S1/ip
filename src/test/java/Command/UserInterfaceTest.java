package Command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class UserInterfaceTest {

    private UserInterface userInterface;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect standard output to capture printed messages
        System.setOut(new PrintStream(outputStream));
        userInterface = new UserInterface();
    }

    @Test
    public void testPrintMessage() {
        userInterface.showMessage("Hello, world!");
        assertEquals("Hello, world!\n", outputStream.toString());
    }

}

