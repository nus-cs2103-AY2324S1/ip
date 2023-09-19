package catbot.io;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

import catbot.bot.Bot;
import catbot.bot.CommandArgumentStruct;
import catbot.internal.Parser;
import catbot.internal.Bounds;
import catbot.internal.NamedParameterMap;
import catbot.task.Task;
import catbot.task.TaskList;

public class CatBotConsoleIo implements UserIo {

    //region Constants
    //http://www.patorjk.com/software/taag/#p=display&h=1&f=3D-ASCII&t=CAT%20BOT
    //font: 3D-ASCII; Character Width: Fitted; Character Height: Default; Text: CAT BOT
    public static final String NAME =
            " ________   ________   _________        ________   ________   _________   \n"
            + "|\\   ____\\ |\\   __  \\ |\\___   ___\\     |\\   __  \\ |\\   __  \\ |\\___   ___\\ \n"
            + "\\ \\  \\___| \\ \\  \\|\\  \\\\|___ \\  \\_|     \\ \\  \\|\\ /_\\ \\  \\|\\  \\\\|___ \\  \\_| \n"
            + " \\ \\  \\     \\ \\   __  \\    \\ \\  \\       \\ \\   __  \\\\ \\  \\\\\\  \\    \\ \\  \\  \n"
            + "  \\ \\  \\____ \\ \\  \\ \\  \\    \\ \\  \\       \\ \\  \\|\\  \\\\ \\  \\\\\\  \\    \\ \\  \\ \n"
            + "   \\ \\_______\\\\ \\__\\ \\__\\    \\ \\__\\       \\ \\_______\\\\ \\_______\\    \\ \\__\\\n"
            + "    \\|_______| \\|__|\\|__|     \\|__|        \\|_______| \\|_______|     \\|__|\n";
    private static final String PREFIX = "\t> ";
    private static final String PREFIX_WARN = "\t! ";

    //https://copypastatext.com/bongo-cat-ascii/

    //endregion

    //region Fields

    private Consumer<String> out;
    private Supplier<String> in;
    private final Parser parser = Parser.with(null);
    private volatile boolean isStillOpen = true;

    //endregion

    //region Constructors

    public CatBotConsoleIo() {
        this(new Scanner(System.in)::nextLine, System.out::print);
    }

    public CatBotConsoleIo(Supplier<String> in, Consumer<String> out) {
        setIn(in);
        setOut(out);
    }

    //endregion

    //region IO setters
    public void setOut(Consumer<String> consumer) {
        this.out = consumer;
    }
    public void setIn(Supplier<String> supplier) {
        this.in = supplier;
    }

    //endregion

    //region UserIo

    @Override
    public void initialize() {
        line();
        out.accept("Hiya! I'm\n" + NAME + "\n");
        line();
    }

    @Override
    public void cleanup() {
        send("Bye!");
        line();
        this.isStillOpen = false;
    }

    @Override
    public boolean isStillOpen() {
        return this.isStillOpen;
    }

    @Override
    public void takeoverExecutionLogic(Bot bot) {
        while (isStillOpen()) {
            bot.run(getNextCommand());
        }
    }

    private CommandArgumentStruct getNextCommand() {
        String nextLine;
        NamedParameterMap namedParameterMap;
        while (true) {
            nextLine = nextLine();
            namedParameterMap = parser.parse(nextLine);
            if (namedParameterMap.keySet().size() == 1) {
                for (String command : namedParameterMap.keySet()) {
                    return new CommandArgumentStruct(command, namedParameterMap.get(command));
                }
            }
        }
    }

    //endregion

    //region ErrorIndicatorIo

    @Override
    public void indicateInvalidCommand(String attemptedCommand) {
        warn("idgi ;-;");
    }

    @Override
    public void indicateInvalidInteger(String attemptedInteger) {
        warn("that doesn't look like a number... number pls");
    }

    @Override
    public void indicateInvalidIndex(int attemptedIndex, Bounds bounds) {
        warn("i expected a number from " + bounds.getLower() + " to " + bounds.getUpper() + "...");
    }

    @Override
    public void indicateArgumentInvalid(InvalidArgumentState invalidState, NamedParameterMap namedParameterMap) {
        switch (invalidState) {
        case PARAMETER_EMPTY:
            for (String arg : namedParameterMap.keySet()) {
                warn(arg + " is empty");
            }
            send("please make sure these arguments are filled!");
            break;
        case PARAMETER_MISSING:
            for (String arg : namedParameterMap.keySet()) {
                warn(arg + " is missing");
            }
            send("please make sure to include them next time!");
            break;
        case NOT_A_DATE:
            for (String arg : namedParameterMap.keySet()) {
                warn(arg + " is set to \"" + namedParameterMap.get(arg) + "\", which is not a date!");
            }
            break;
        default:
            throw new RuntimeException();
        }
    }

    //endregion

    //region TaskAssistantIo

    @Override
    public void displayTaskList(TaskList taskList) {
        int i = 1;
        int intlen = 0;
        for (int len = taskList.size(); len > 0; intlen++) {
            len /= 10;
        }
        for (String taskString : taskList.getTaskStrings()) {
            send(String.format("%" + intlen + "d", i++) + ". " + taskString);
        }
    }

    @Override
    public void displayTaskAdded(TaskList taskList) {
        int index = taskList.size() - 1;
        send("Added: " + (index + 1) + ". " + taskList.getTask(index));
    }

    @Override
    public void displayTaskDeleted(Task deleted) {
        send("Deleted: " + deleted);
    }

    @Override
    public void displayTaskModified(TaskList taskList, int index) {
        send((index + 1) + ". " + taskList.getTask(index));
    }
    //endregion

    //region Internal Helper

    private String format(String s, String prefix) {
        if (s == null) {
            return "";
        }
        return s.replaceAll("(^|\n)", "$1" + prefix);
    }

    private void line() {
        out.accept("───────────────────────────────────────────────────────────────────────────\n");
    }

    private void warn(String s) {
        out.accept(format(s, PREFIX_WARN) + "\n");
    }

    private void send(String s) {
        if (s == null) {
            return;
        }
        out.accept(format(s, PREFIX) + "\n");
    }

    private String nextLine() {
        return this.in.get();
    }

    //endregion

}
