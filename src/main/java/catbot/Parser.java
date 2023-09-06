package catbot;

import catbot.internal.NamedParameterMap;

import java.util.Arrays;

public class Parser {

    private final String delimiter;
    private boolean emptyArgument;

    //region Constructor

    private Parser(String delimiter, boolean emptyArgument) {
        this.delimiter = delimiter;
        this.emptyArgument = emptyArgument;
    }

    public static Parser by(String delimiter) {
        return by(delimiter, false);
    }

    public static Parser by(String delimiter, boolean emptyArgument) {
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
            NamedParameterMap cmdArgs = new NamedParameterMap();
            parseCmdArg(s, cmdArgs);
            return cmdArgs;
        }
    }

    public NamedParameterMap parse(String s) {
        NamedParameterMap cmdArgs = new NamedParameterMap();
        String[] arr = s.split(delimiter);
        parseCmdArg(arr[0], cmdArgs, this.emptyArgument);
        Arrays.stream(arr).skip(1).forEach(segment -> parseCmdArg(segment, cmdArgs));
        return cmdArgs;
    }

    //region Internal Helpers

    /**
     * Split string into one pair of command + argument based on the first whitespace.
     * @param s string containing both command and argument, in that order
     * @param map to store the mapping between command and argument
     */
    private static void parseCmdArg(String s, NamedParameterMap map) {
        String[] cmdArg = s.split("\\s", 2);
        if (cmdArg.length == 2) {
            map.put(cmdArg[0].trim(), cmdArg[1].trim());
        } else {
            map.put(cmdArg[0].trim(), "");
        }
    }

    private static void parseCmdArg(String s, NamedParameterMap map, boolean emptyArgument) {
        if (emptyArgument) {
            map.put("", s.trim());
        } else {
            parseCmdArg(s, map);
        }
    }

    //endregion

}
