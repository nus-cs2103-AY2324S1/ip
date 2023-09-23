package ui.cli;

import kniaz.logic.command.KniazCommand;
import ui.KniazOutputFlavourer;


/**
 * Class handling printing of output, including delegation of how to 'pretty' up output to user
 */
public class KniazOutputController {


    private static final String GREETING = "Hello from KNIAZ";
    private static final String SEPERATOR = "_".repeat(20);

    private static final KniazOutputFlavourer DEFAULT_FLAVOURER = new KniazOutputFlavourer();

    private KniazOutputFlavourer flavourer;

    /**
     * Constructor for KniazOutputController
     */
    public KniazOutputController() {
        this.flavourer = DEFAULT_FLAVOURER;
    }

    /**
     * Prints line to output with a seperator line beneath.
     * @param toPrint the line to print to output
     * @return the successfully printed line
     */
    public String printToOutput(String toPrint) {
        System.out.println(toPrint);
        System.out.println(SEPERATOR);
        return toPrint;
    }

    /**
     * Prints the expected startup message to output, using same format as printToOutput
     * @return the expected startup message
     */
    public String printStartupMessage() {
        return printToOutput(GREETING);
    }

    /**
     * Retrives the 'flavour' string for a given Command, which is defined as a string printed purely
     * to give the chatbot more 'personality'.
     * @param command the KniazCommand to retrieve the flavour string for
     * @return the flavour string
     */
    public String getFlavourFor(KniazCommand command) {
        return this.flavourer.getFlavourFor(command.getInstruct());
    }
}
