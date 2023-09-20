package catbot.internal;

import java.util.Arrays;

/**
 * Object that parses a String into a NamedParameterMap.
 */
public class Parser {

    private final String delimiter;
    private final boolean willKeepEmptyArgument;

    //region Constructor

    private Parser(String delimiter, boolean willKeepEmptyArgument) {
        this.delimiter = delimiter;
        this.willKeepEmptyArgument = willKeepEmptyArgument;
    }

    /**
     * Returns a parser that uses the provided String as a delimiter.
     *
     * @param delimiter String to use as a trigger to start new commands.
     * @return Parser constructed with the provided delimiter.
     */
    public static Parser with(String delimiter) {
        return with(delimiter, false);
    }

    /**
     * Returns a parser that uses the provided String as a delimiter.
     *
     * @param delimiter             String to use as a trigger to start new commands.
     * @param willKeepEmptyArgument true if the first parameter should have an empty name.
     * @return Parser constructed with the provided delimiter.
     */
    public static Parser with(String delimiter, boolean willKeepEmptyArgument) {
        if (delimiter == null || delimiter.isEmpty()) {
            return new SingleParser();
        } else {
            return new Parser(delimiter, willKeepEmptyArgument);
        }
    }
    //endregion

    private static class SingleParser extends Parser {

        private SingleParser() {
            super(null, false);
        }

        @Override
        public NamedParameterMap parse(String s) {
            NamedParameterMap map = new NamedParameterMap();
            parseCommandArgumentString(s, map);
            return map;
        }
    }

    /**
     * Apply the parser to the given String, and return a representation of parameter-argument pairs.
     *
     * @param s String to parse.
     * @return NamedParameterMap with commands as keys, and arguments as values.
     */
    public NamedParameterMap parse(String s) {
        NamedParameterMap map = new NamedParameterMap();
        String[] commandArgumentStrings = s.split(delimiter);

        //first command potentially gets the empty argument treatment ("" -> value)
        parseCommandArgumentString(commandArgumentStrings[0], map, this.willKeepEmptyArgument);
        Arrays.stream(commandArgumentStrings).skip(1).forEach(segment -> parseCommandArgumentString(segment, map));
        return map;
    }

    //region Internal Helpers

    /**
     * Split string into one pair of command + argument based on the first whitespace.
     * @param s string containing both command and argument, in that order
     * @param map to store the mapping between command and argument
     */
    private static void parseCommandArgumentString(String s, NamedParameterMap map) {
        String[] splitCommandArgument = s.split("\\s", 2);
        if (splitCommandArgument.length == 2) {
            map.addNamedParameter(splitCommandArgument[0].trim(), splitCommandArgument[1].trim());
        } else {
            map.addNamedParameter(splitCommandArgument[0].trim(), "");
        }
    }

    private static void parseCommandArgumentString(String s, NamedParameterMap map, boolean willKeepEmptyArgument) {
        if (willKeepEmptyArgument) {
            map.addNamedParameter("", s.trim());
        } else {
            parseCommandArgumentString(s, map);
        }
    }

    //endregion

}
