package io;
import java.util.function.Function;

import client.RockException;
import commands.Commands;
/**
 * Used to handle input given by chatbot
 * and call the appropriate commands with
 * the parsed data
 * @author Alvis Ng (supermii2)
 */
public class Invoker {
    private Commands commands;
    public Invoker(Commands commands) {
        this.commands = commands;
    }
    /**
     * Helper function used to obtain the rest of a sentence sans keyword.
     * @param sentence String to be trimmed.
     * @return Same string without keyword
     */
    private static String removeFirstWord(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
    /**
     * Used to handle a given user input and call the corresponding method.
     * @param inputString User's input.
     */
    public String handle(String inputString) throws RockException {
        Parser input = new Parser(removeFirstWord(inputString));
        String keyword = inputString.split(" ")[0];
        try {
            Function<Parser, String> command = this.commands.getCommand(keyword);
            return (command.apply(input));
        } catch (IllegalArgumentException e) {
            throw new RockException(e.getMessage());
        }
    }
}
