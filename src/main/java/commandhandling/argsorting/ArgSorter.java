package commandhandling.argsorting;

import exceptions.syntax.KniazInvalidArgsException;

import java.util.*;
import java.util.stream.Stream;

public abstract class ArgSorter {
    public static String[] sortArgsByStarting(String[] args, String[] startingOrder) throws KniazInvalidArgsException {

        if (args.length != startingOrder.length) {
            throw new KniazInvalidArgsException();
        }

        List<String> outAsList = new ArrayList<>();
        Queue<String> argsAsQueue = new ArrayDeque<>(Arrays.asList(args));

        while (!argsAsQueue.isEmpty()){
            String currArg = argsAsQueue.remove();

            for (String starting : startingOrder){

                if (currArg.startsWith(starting)){

                    outAsList.add(removeLeadingSubstr(currArg,starting));
                    break;
                }

            }

        }

        if (outAsList.size() != startingOrder.length) {
            throw new KniazInvalidArgsException();
        }

        return outAsList.toArray(new String[0]);

    }

    private static String removeLeadingSubstr(String str, String substr) {
        return str.startsWith(substr) ? str.substring(substr.length()) : str;
    }
}
