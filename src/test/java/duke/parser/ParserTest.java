package duke.parser;

import duke.Duke;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Duke.changeFilePath("./data/duketest.txt");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        Duke.changeFilePath("./data/duke.txt");
    }

    @Test
    public void parse_invalidInput_invalidInputMessageShown(){
        Parser parser = new Parser(new Ui("Moira"));
        parser.parse(" ");
        parser.parse("bleh");
        parser.parse("sdaf sd asdd");
        assertEquals("--------------------------------------------------------------------------" +
                "\nHOLD UP! What on earth do you mean??" +
                "\n--------------------------------------------------------------------------" +
                "\n--------------------------------------------------------------------------" +
                "\nHOLD UP! What on earth do you mean??" +
                "\n--------------------------------------------------------------------------" +
                "\n--------------------------------------------------------------------------" +
                "\nHOLD UP! What on earth do you mean??" +
                "\n--------------------------------------------------------------------------", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void parse_byeCommandVariations_isReceivingInputChangeAccordingly(){
        Parser parser = new Parser(new Ui("Moira"));
        Duke.setIsReceivingInput(true);
        parser.parse("bye s");
        assertEquals(true, Duke.getIsReceivingInput());
        parser.parse("by");
        assertEquals(true, Duke.getIsReceivingInput());
        parser.parse("bye ");
        assertEquals(false, Duke.getIsReceivingInput());
    }
}
