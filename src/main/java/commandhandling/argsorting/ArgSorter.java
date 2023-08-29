package commandhandling.argsorting;

import exceptions.syntax.KniazInvalidArgsException;

import java.util.*;
import java.util.stream.Stream;

public abstract class ArgSorter {
    /**
     * Sorts a list of arguments according to the substring they start with, output will match
     * order of startingOrder. Will match greedily, i.e. taking the first match as the match in the final order.
     * I.e. if args = ["second_bla_bla","first_bla_bla"], startingOrder = ["first","second"],
     * will return ["first_bla_bla, "second_bla_bla"]
     *
     * @param args the list of arguments to sort
     * @param startingOrder the order to sort, based on the leading substring
     * @return the sorted list
     * @throws KniazInvalidArgsException if either : args and startingOrder have length mismatch,
     * or this method was unable to match a startingOrder substring.
     */
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
