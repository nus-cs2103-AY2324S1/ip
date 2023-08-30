package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {
    @Test
    public void printHelloTest() {
        Ui ui = new Ui();
        String br = ui.br;
        String expectedOutput =br +
                "Hello! I'm Jose Mourinho, a task planning bot that will record your tasks.\n" +
                "If you require help, type \"help\"\n" +
                br;
        assertEquals(expectedOutput, ui.printHello());
    }
    
}
