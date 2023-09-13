package catbot;

import catbot.internal.NamedParameterMap;

import java.util.Arrays;

public class Parser {

    private final String delimiter;
    private final boolean emptyArgument;

    //region Constructor

    private Parser(String delimiter, boolean emptyArgument) {
        this.delimiter = delimiter;
        this.emptyArgument = emptyArgument;
    }

    public static Parser with(String delimiter) {
        return with(delimiter, false);
    }

    public static Parser with(String delimiter, boolean emptyArgument) {
        if (delimiter == null || delimiter.isEmpty()) {
            return new SingleParser();
        } else {
            return new Parser(delimiter, emptyArgument);
        }
    }
    //endregion

    public static class SingleParser extends Parser {

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

    public NamedParameterMap parse(String s) {
        NamedParameterMap map = new NamedParameterMap();
        String[] commandArgumentStrings = s.split(delimiter);
        //first command potentially gets the empty argument treatment ("" -> value)
        parseCommandArgumentString(commandArgumentStrings[0], map, this.emptyArgument);
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

    private static void parseCommandArgumentString(String s, NamedParameterMap map, boolean emptyArgument) {
        if (emptyArgument) {
            map.addNamedParameter("", s.trim());
        } else {
            parseCommandArgumentString(s, map);
        }
    }

    //endregion

}
