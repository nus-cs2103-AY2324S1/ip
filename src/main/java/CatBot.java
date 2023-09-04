import java.util.HashMap;
import java.util.function.Function;

public class CatBot {

    //region Command Patterns
    public enum CommandPattern {
        IndexArgument((s) -> {

        }),
        ForwardSlashEscapedArguments;

        private final Function<String, NamedParameterMap> parser;
        private CommandPattern(Function<String, NamedParameterMap> parser) {
            this.parser = parser;
        }
        public NamedParameterMap parse(String s) {
            return parser.apply(s);
        }
    }
    //endregion

    //region Failure States
    public enum CatBotFailureState {
        INVALID_INDEX
    }
    //endregion


    //region Supported Commands
    public enum Commands {
        NULL(null), LIST("list"), BYE("bye"), MARK("mark"), UNMARK("unmark"),
        TODO("todo"), DEADLINE("deadline"), EVENT("event"),
        DELETE("delete");
        private final String invocation;

        private Commands(String invocation) {
            this.invocation = invocation;
        }
        private static final HashMap<String, Commands> cmdset = new HashMap<>();

        public static Commands match(String s) {
            if (s == null) return Commands.NULL;
            Commands match = cmdset.get(s.toLowerCase());
            if(match == null) return Commands.NULL;
            else return match;
        }

        static {
            for (Commands cmd : values()) {
                cmdset.put(cmd.invocation, cmd);
            }
        }
    }

    private static final CommandMap commands = new CommandMap();
    static {
        commands.addCommand(null, );
    }
    //endregion

    public static void main(String[] args) {
        FlowController flowController = new FlowController();
        TaskList taskList = new TaskList();

    }
}
