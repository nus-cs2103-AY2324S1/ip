package duke;  //same package as the class being tested

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut); // restore the original standard out to avoid issues with other tests
    }

    @Test
    public void startUpTest(){
//
//        Duke.main(null);

        String actualOutput = outputStreamCaptor.toString().trim();
        String expected = "____________________________________________________________\n" +
                "Hello! I'm chatbot\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n" +
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        assertEquals(expected, actualOutput);
    }


}