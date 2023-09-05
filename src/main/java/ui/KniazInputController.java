package ui;

import main.logic.command.KniazCommand;
import ui.inputparser.KniazLineParser;

import java.util.Scanner;

/**
 * Class encapsulating a controller for input, takes in input from system.in and delegates parsing to
 * relevant classes
 */
public class KniazInputController {



    private KniazLineParser parser;
    private Scanner input;

    /**
     * Constructor for KniazInputController
     */
    public KniazInputController(){
        this.parser = new KniazLineParser();
        this.input = new Scanner(System.in);
    }


    /**
     * Reads in the next line from input and automatically parses to a KniazCommand
     * @return the KniazCommand representing the commmand that was entered into input
     */
    public KniazCommand nextLine(){
        return readCommand(input.nextLine());
    }

    private KniazCommand readCommand(String line) {
        // passes a line off to the parser to parse into a Command
        return parser.parseLine(line);
    }






}
