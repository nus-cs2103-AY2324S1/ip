package ui.inputparser;


import exceptions.syntax.KniazInvalidArgsException;
import exceptions.syntax.KniazInvalidCommandException;
import exceptions.syntax.KniazSyntaxException;
import ui.inputparser.commands.CommandFactory;
import ui.inputparser.commands.KniazCommand;

import java.util.Arrays;

/**
 * Class encapsulating logic of parsing & tokenizing commands given to Kniaz
 */
public class KniazLineParser {

    /**
     *
     * @param line the line to parse
     * @return the command that line represents, including the arguments
     * @throws KniazInvalidArgsException when the arguments are wrongly formatted
     */
    public  KniazCommand<?> parseLine(String line) throws KniazInvalidArgsException {
        String[] tokenizedLine = tokenizeLine(line);



        String instructAsString = tokenizedLine[0];
        InstructionType instruct = InstructionType.stringToInstrType(instructAsString);

        String[] args;
        if (tokenizedLine.length >= 2) {
            // if there is one token that is not the instruction (i.e. at least 1 argument)
            args = Arrays.copyOfRange(tokenizedLine, 1, tokenizedLine.length - 1);
        } else {
            // empty list
            args = new String[]{};
        }
        return CommandFactory.parseCommand(instruct,args);
    }

    private static String[] tokenizeLine(String line) {
        return line.strip().split(" *"); //split by whitespaces
    }



}
