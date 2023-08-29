package ui.inputparser;


import exceptions.syntax.KniazInvalidArgsException;
import main.logic.command.CommandFactory;
import main.logic.command.KniazCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Class encapsulating logic of parsing & tokenizing commands given to Kniaz
 */
public class KniazLineParser {

    private static String NAMED_ARG_MARKER = "\\s*/\\s*" ;
    // any number >= 0 of whitespace wrapping a '/'

    /**
     *
     * @param line the line to parse
     * @return the command that line represents, including the arguments
     * @throws KniazInvalidArgsException when the arguments are wrongly formatted
     */


    public  KniazCommand parseLine(String line) throws KniazInvalidArgsException {


        Scanner lineScanner = new Scanner(line + " ");

        String instructAsString = getInstructionString(lineScanner);

        InstructionType instruct = InstructionType.stringToInstrType(instructAsString);

        ArrayList<String> unnamedArgs = getUnnamedArgs(lineScanner, instruct.numUnnamedArgs);

        HashMap<String,String> namedArgs = getNamedArgs(lineScanner);



        return CommandFactory.makeCommand(instruct,unnamedArgs,namedArgs);
    }

    private static String getUntilWhiteSpace(Scanner toRead) {


        // default reads until whitespace
        return toRead.next();
    }


    private static String getInstructionString(Scanner toRead){
        return getUntilWhiteSpace(toRead);
    }

    private static ArrayList<String> getUnnamedArgs(Scanner toRead, int numArgs){
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < numArgs; i++){
            out.add(getUntilWhiteSpace(toRead));
        }
        return out;
    }

    private static HashMap<String,String> getNamedArgs(Scanner toRead){
        HashMap<String,String> out = new HashMap<>();
        toRead.useDelimiter(NAMED_ARG_MARKER);
        while (toRead.hasNext()){
            String nextToken = toRead.next();
            String[] splitToken = nextToken.split("\\s+",2);
            //split by any number of whitespaces

            String argName = splitToken[0];
            String argValue = splitToken[1];
            out.put(argName,argValue);
        }
        return out;
    }




}
