package alcazar;

import alcazar.commands.AddEventCommand;
import alcazar.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddEventCommandTest {
    @Test
    void getCommandContentTest() {
        try {
            assertEquals("birthday /from 2pm /to 6pm",
                    new AddEventCommand("event birthday /from 2pm /to 6pm").getCommandContent());
        } catch (InvalidArgumentException e) {
            System.out.println("Exception thrown");
        }
    }

    @Test
    void extractEventContentsTest() {
        assertEquals("birthday", new AddEventCommand("event birthday /from 2pm /to 6pm")
                .extractEventContents("birthday /from 2pm /to 6pm")[0]);
        assertEquals("2pm", new AddEventCommand("event birthday /from 2pm /to 6pm")
                .extractEventContents("birthday /from 2pm /to 6pm")[1]);
    }

}
