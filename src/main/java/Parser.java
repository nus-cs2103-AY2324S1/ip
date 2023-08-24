public class Parser {

    public class CmdArg {
        public final String command;
        public final String arg;

        public CmdArg(String cmd, String arg){
            this.command = cmd; this.arg = arg;
        }
    }

    private int currentIndex;
    private String toParse;
    private char delimiter;

    public Parser (char delimiter) {
        this.delimiter = delimiter;
    }

    public Parser load(String s) {
        this.currentIndex = 0;
        this.toParse = s.trim();
        return this;
    }

    public CmdArg parse() {
        if (currentIndex >= toParse.length()) return null;

        int index;
        CmdArg cmdArg;

        String cmd;
        if (toParse.charAt(currentIndex) != this.delimiter) cmd = "";
        else {
            index = toParse.indexOf(" ", currentIndex);
            if (index == -1) {
                cmdArg = new CmdArg(toParse.substring(currentIndex + 1), null);
                currentIndex = toParse.length();
                return cmdArg;
            } else {
                cmd = toParse.substring(currentIndex + 1, index);
                currentIndex = index+1;
            }
        }

        String arg;
        index = toParse.indexOf(this.delimiter, currentIndex);
        if (index == -1) {
            cmdArg = new CmdArg(cmd, toParse.substring(currentIndex).trim());
            currentIndex = toParse.length();
        } else {
            cmdArg = new CmdArg(cmd, toParse.substring(currentIndex, index).trim());
            currentIndex = index;
        }
        return cmdArg;
    }

}
