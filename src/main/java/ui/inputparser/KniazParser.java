package ui.inputparser;


import exceptions.syntax.KniazInvalidCommandException;
import exceptions.syntax.KniazSyntaxException;
import ui.inputparser.commands.CommandFactory;
import ui.inputparser.commands.KniazCommand;

import java.util.Arrays;

/**
 * Abstract class encapsulating logic of parsing & tokenizing commands given to Kniaz
 */
public abstract class KniazParser {

    public static KniazCommand parseLine(String line) throws KniazSyntaxException, KniazInvalidCommandException {
        String[] tokenizedLine = tokenizeLine(line);

        String instructAsString = tokenizedLine[0];
        InstructionType instruct = InstructionType.stringToInstrType(instructAsString);

        String[] args = Arrays.copyOfRange(tokenizedLine,1,tokenizedLine.length - 1);

        return CommandFactory.parseCommand(instruct,args);
    }

    private static String[] tokenizeLine(String line) {
        return line.strip().split(" *"); //split by whitespaces
    }
    // this won't work


}
