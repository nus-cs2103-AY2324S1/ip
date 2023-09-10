package jarvis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JarvisTest {

    private Jarvis jarvis;

    @BeforeEach
    public void setUp() {
        this.jarvis = new Jarvis();
    }

    @Test
    public void testInitialization() {
        assertNotNull(jarvis);
    }

    @Test
    public void testStart() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        jarvis.start();

        String output = outputStream.toString();
        assertTrue(output.contains("Hi Master! I'm your personal assistant: JARVIS!"));
    }
}
