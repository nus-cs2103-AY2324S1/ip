package ui;

import exceptions.syntax.KniazInvalidArgsException;
import main.logic.command.KniazCommand;
import main.logic.handler.CommandHandler;
import ui.inputparser.KniazLineParser;

import java.util.Scanner;

/**
 * Handles parsing of input
 *
 */
public class KniazInputController {
    //SHOULD : hand off all inputs to be parsed and pass up outputs from them
    //CONSIDER : how are we going to spice up the messages?

    private KniazLineParser parser;
    private Scanner input;
    public KniazInputController(){
        this.parser = new KniazLineParser();
        this.input = new Scanner(System.in);
    }



    public KniazCommand nextLine(){
        return readCommand(input.nextLine());
    }
    public KniazCommand readCommand(String line) {
        return parser.parseLine(line);
    }






}
