package juke.parsers;

import java.util.regex.Pattern;

import juke.commons.classes.JukeObject;

/**
 * Contains methods used to parse the commands given by the user. Note that excessive whitespaces surrounding
 * command arguments will be ignored and the command will be treated as if there were no excessive
 * whitespaces in the first place, mimicking how commands are parsed in shells like zsh and bash.
 * <p>
 * This parser may not be instantiated. All methods are exposed via static methods.
 */
public abstract class Parser extends JukeObject {
    /** String regex for splitting a command by any number of spaces. */
    private static final String SPACE_REGEX = "\\s+";

    /**
     * String regex for checking a command by "/by", preceded or proceeded by any number of whitespaces
     * or characters.
     */
    private static final String CHECKING_BY_STRING_REGEX = ".*\\s+/by\\s+.*";

    /** String regex for splitting a command by "/by", preceded or proceeded by any number of whitespaces. */
    private static final String SPLITTING_BY_STRING_REGEX = "\\s+/by\\s+";

    /**
     * String regex for checking a command for "/from" and "/to", preceded or proceeded by any number of whitespaces
     * or characters.
     */
    private static final String CHECKING_FROM_TO_STRING_REGEX = ".*\\s+/from\\s+.*\\s+/to\\s+.*";

    /**
     * String regex for splitting a command by "/from" and "/to", , preceded or proceeded by any number of whitespaces.
     */
    private static final String SPLITTING_FROM_TO_STRING_REGEX = "\\s+/from\\s+|\\s+/to\\s+";

    /**
     * Parses a command by a space. This method is used to obtain the first command in the command chain.
     *
     * @param command Raw Command
     * @return Parsed array of Strings which represents the tokens in the command.
     */
    public static String[] parseBySpace(String command) {
        return command.strip().split(Parser.SPACE_REGEX);
    }

    /**
     * Parses a command by the String "/by". This method is to be invoked only on a cleaned String where
     * the first starting command is discarded.
     *
     * @param command Processed Command
     * @return Parsed array of String which represents the parsed tokens in the command
     */
    public static String[] parseByByString(String command) {
        return command.strip().split(Parser.SPLITTING_BY_STRING_REGEX);
    }

    /**
     * Parses a command by the String "/from" and "/to". This method is to be invoked only on a
     * cleaned String where the first starting command is discarded.
     *
     * @param command Processed Command
     * @return Parsed array of String which represents the parsed tokens in the command
     */
    public static String[] parseByFromToString(String command) {
        return command.strip().split(Parser.SPLITTING_FROM_TO_STRING_REGEX);
    }

    /**
     * Checks if the input string matches the "/by" sequence.
     *
     * @param command String to check
     * @return true if the input string matches, else false
     */
    public static boolean isMatchByString(String command) {
        return Pattern.matches(Parser.CHECKING_BY_STRING_REGEX, command);
    }

    /**
     * Checks if the input string matches the "/from ... /to" sequence.
     *
     * @param command String to check
     * @return true if the input string matches, else false
     */
    public static boolean isMatchFromToString(String command) {
        return Pattern.matches(Parser.CHECKING_FROM_TO_STRING_REGEX, command);
    }
}
