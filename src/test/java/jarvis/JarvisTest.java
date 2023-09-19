package jarvis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
