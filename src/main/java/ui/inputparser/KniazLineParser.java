package ui.inputparser;


import exceptions.syntax.KniazInvalidArgsException;
import main.logic.command.CommandFactory;
import main.logic.command.KniazCommand;

import java.util.*;


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

        List<String> unnamedArgs = getUnnamedArgs(lineScanner, instruct.numUnnamedArgs);

        Map<String,String> namedArgs = getNamedArgs(lineScanner);



        return CommandFactory.makeCommand(instruct,unnamedArgs,namedArgs);
    }

    private static String getUntilWhiteSpace(Scanner toRead) {


        // default reads until whitespace
        return toRead.next();
    }


    private static String getInstructionString(Scanner toRead){
        return getUntilWhiteSpace(toRead);
    }
    // on a fresh line/Scanner, it will just be until the first whitespace

    private static ArrayList<String> getUnnamedArgs(Scanner toRead, int numArgs){
        // Default behaviour is to consume the first _n_ arguments as unnamed args
        // assume they are whitespace-delimited
        // If there is an error in this assumption, parsing will be strange,
        // and possibly result in a thrown exception when trying to execute command
        // But it's difficult to impossible to fully validate user input this way
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < numArgs; i++){
            out.add(getUntilWhiteSpace(toRead));
        }
        return out;
    }


    private static Map<String,String> getNamedArgs(Scanner toRead){
        // gets the named args, marked by '/' characters
        // Assumes they come in key-value pairs
        // So it assumes /from 2pm is a key value pair mapping
        // 'from' to '2pm'
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
