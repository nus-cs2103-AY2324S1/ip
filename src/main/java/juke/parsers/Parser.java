package main.java.juke.parsers;

import main.java.juke.core.JukeObject;

import java.util.regex.Pattern;

/**
 * Class that helps to parse the commands given by the user.
 * <p>
 * This parser may not be instantiated. All methods are exposed via
 * static methods.
 */
public abstract class Parser extends JukeObject {
    /** String regex for splitting a command by spaces. */
    private static final String SPACE_REGEX = " ";

    /** String regex for checking a command by "/by". */
    private static final String CHECKING_BY_REGEX = ".* /by .*";

    /** String regex for splitting a command by "/by". */
    private static final String SPLITTING_BY_REGEX = " /by ";

    /** String regex for checking a command for "/from" and "/to". */
    private static final String CHECKING_FROM_TO_REGEX = ".* /from .*|.* /to .*";

    /** String regex for splitting a command by "/from" and "/to". */
    private static final String SPLITTING_FROM_TO_REGEX = " /from | /to ";

    /**
     * Parse a command by a space. This method is used to obtain the first command in the command chain.
     * @param command Raw Command
     * @return Parsed array of Strings which represents the tokens in the command.
     */
    public static String[] parseBySpace(String command) {
        return command.strip().split(Parser.SPACE_REGEX);
    }

    /**
     * Parse a command by the String "/by". This method is to be invoked only on a cleaned String where
     * the first starting command is discarded.
     * @param command Processed Command
     * @return Parsed array of String which represents the parsed tokens in the command
     */
    public static String[] parseByByString(String command) {
        return command.strip().split(Parser.SPLITTING_BY_REGEX);
    }

    /**
     * Parse a command by the String "/from" and "/to. This method is to be invoked only on a
     * cleaned String where the first starting command is discarded.
     * @param command Processed Command
     * @return Parsed array of String which represents the parsed tokens in the command
     */
    public static String[] parseByFromToString(String command) {
        return command.strip().split(Parser.SPLITTING_FROM_TO_REGEX);
    }

    public static boolean isMatchByString(String command) {
        return Pattern.matches(Parser.CHECKING_BY_REGEX, command);
    }

    public static boolean isMatchFromToString(String command) {
        return Pattern.matches(Parser.CHECKING_FROM_TO_REGEX, command);
    }
}
