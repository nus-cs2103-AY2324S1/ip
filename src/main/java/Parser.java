import java.util.Arrays;

public class Parser {

    private final String delimiter;

    //region Constructor
    private Parser(String delimiter) {
        this.delimiter = delimiter;
    }

    public Parser by(String delimiter) {
        if (delimiter == null || delimiter.isEmpty()) {
            return new SingleParser();
        } else {
            return new Parser(delimiter);
        }
    }
    //endregion

    public class SingleParser extends Parser {

        private SingleParser() {
            super(null);
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
        Arrays.stream(s.split(delimiter)).forEach((segment) -> parseCmdArg(segment, cmdArgs));
        return cmdArgs;
    }

    //region Internal Helpers
    private void parseCmdArg(String s, NamedParameterMap map) {
        String[] cmdArg = s.split("\\s", 2);
        map.put(cmdArg[0], cmdArg[1]);
    }
    //endregion

}
