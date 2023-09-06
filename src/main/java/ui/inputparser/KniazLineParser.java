package ui.inputparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.syntax.MissingUnnamedArgsException;
import kniaz.logic.command.CommandFactory;
import kniaz.logic.command.KniazCommand;




/**
 * Class encapsulating logic of parsing & tokenizing commands given to kniaz.Kniaz
 */
public class KniazLineParser {


    private static final String ANYNUM_WHITESPACE = "\\s+";

    private static final String NAMED_ARG_MARKER = "/";
    private static final String NAMED_ARG_REGEX = "\\s*" + NAMED_ARG_MARKER;
    // this will change with whatever NAMED_ARG_MAKER is

    // any number >= 0 of whitespace wrapping a '/'

    /**
     * Parses the line given into its command as a KniazCommand
     * @param line the line to parse
     * @return the command that line represents, including the arguments
     * @throws MissingUnnamedArgsException when the arguments are wrongly formatted
     */
    public KniazCommand parseLine(String line) {

        List<String> firstSplit = splitInstructArgs(line);
        String instructAsString = firstSplit.get(0);
        String allArgs = firstSplit.get(1);

        InstructionType instruct = InstructionType.stringToInstrType(instructAsString);


        List<String> splittedArgs = splitArgTypes(allArgs);
        // take the first instance of a named arg pattern as where the named args start
        String unnamedArgsString = splittedArgs.get(0);
        String namedArgsString = splittedArgs.get(1);

        List<String> unnamedArgs = getUnnamedArgs(unnamedArgsString, instruct.numUnnamedArgs);
        Map<String, String> namedArgs = getNamedArgs(namedArgsString);



        return CommandFactory.makeCommand(instruct , unnamedArgs, namedArgs);
    }

    private static List<String> splitInstructArgs(String original) {
        List<String> out = new ArrayList<>(List.of(original.split(ANYNUM_WHITESPACE, 2)));
        // splits by the whitespace into instruction and arguments to instruction
        while (out.size() < 2) {
            out.add("");
        }
        return out;
    }
    private static List<String> splitArgTypes(String original) {
        // splits into named and unnamed, going off the first '/' marker
        if (!original.contains(NAMED_ARG_MARKER)) {
            return List.of(original, "");
        }

        int indexOfMarker = original.indexOf(NAMED_ARG_MARKER);

        return List.of(original.substring(0, indexOfMarker),
                original.substring(indexOfMarker));

    }

    private static List<String> getUnnamedArgs(String toRead, int numArgs) {
        // Default behaviour is to consume the first _n_ arguments as unnamed args
        // assume they are whitespace-delimited
        // If there is an error in this assumption, parsing will be strange,
        // and possibly result in a thrown exception when trying to execute command
        // But it's difficult to impossible to fully validate user input this way

        return Arrays.asList(toRead.split(ANYNUM_WHITESPACE, numArgs));
    }


    private static Map<String, String> getNamedArgs(String toRead) {
        // gets the named args, marked by '/' characters
        // Assumes they come in key-value pairs
        // So it assumes /from 2pm is a key value pair mapping
        // 'from' to '2pm'
        HashMap<String, String> out = new HashMap<>();

        if (!toRead.contains(NAMED_ARG_MARKER)) {
            return out; //return an empty map because no named arguments
        }

        int firstMarkerIndex = toRead.indexOf(NAMED_ARG_MARKER);
        String namedArgs = toRead.substring(firstMarkerIndex + 1);
        // strip away the named arg marker, should normally be in the first spot, but in case, we search instead
        // We rely on split later, so getting rid of the first ensures all named arg tokens have the marker
        // stripped away.


        String[] tokens = namedArgs.split(NAMED_ARG_REGEX);

        for (String token : tokens) {
            String[] splitToken = token.split(ANYNUM_WHITESPACE, 2);
            String key = splitToken[0];
            String value = splitToken[1];
            out.put(key, value);
        }

        return out;
    }




}
