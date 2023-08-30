package ui;

import main.logic.command.KniazCommand;
import ui.inputparser.InstructionType;

/**
 * Handles logic of UI
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

    public String printToOutput(String toPrint){
        System.out.println(toPrint);
        System.out.println(SEPERATOR);
        return toPrint;
    }

    public String printStartupMessage(){
        return printToOutput(GREETING);
    }

    public  String getFlavourFor(KniazCommand command) {
        return this.flavourer.getFlavourFor(command.getInstruct());
    }
}
