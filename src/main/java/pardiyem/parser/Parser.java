package pardiyem.parser;
public class Parser {
    String SPACE = " ";
    public int parseCommand(String in) {
        int x = in.indexOf(SPACE);
        String command;
        if (x == -1) {
            command = in;
        } else {
            command =  in.substring(0, x);
        }
        switch (command) {
            case "bye":
                return 1;
            case "list":
                return 2;
            default:
                return 0;
        }
    }
}
