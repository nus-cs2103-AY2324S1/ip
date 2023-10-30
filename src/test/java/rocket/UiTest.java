package rocket;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    @Test
    public void showTasks_sampleTasks_success(){
        String response = "Test response";
        Ui ui = new Ui();
        ui.setLastResponse(response);

        assertEquals(ui.getLastResponse(), response);



    }

}
