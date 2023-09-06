package catbot.io;

import catbot.Parser;
import catbot.internal.NamedParameterMap;
import catbot.io.UserIo;
import catbot.task.Task;
import catbot.task.TaskList;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CatbotConsoleIO implements UserIo {

    //region Constants
    //http://www.patorjk.com/software/taag/#p=display&h=1&f=3D-ASCII&t=CAT%20BOT
    //font: 3D-ASCII; Character Width: Fitted; Character Height: Default; Text: CAT BOT
    public static final String NAME =
            " ________   ________   _________        ________   ________   _________   \n" +
            "|\\   ____\\ |\\   __  \\ |\\___   ___\\     |\\   __  \\ |\\   __  \\ |\\___   ___\\ \n" +
            "\\ \\  \\___| \\ \\  \\|\\  \\\\|___ \\  \\_|     \\ \\  \\|\\ /_\\ \\  \\|\\  \\\\|___ \\  \\_| \n" +
            " \\ \\  \\     \\ \\   __  \\    \\ \\  \\       \\ \\   __  \\\\ \\  \\\\\\  \\    \\ \\  \\  \n" +
            "  \\ \\  \\____ \\ \\  \\ \\  \\    \\ \\  \\       \\ \\  \\|\\  \\\\ \\  \\\\\\  \\    \\ \\  \\ \n" +
            "   \\ \\_______\\\\ \\__\\ \\__\\    \\ \\__\\       \\ \\_______\\\\ \\_______\\    \\ \\__\\\n" +
            "    \\|_______| \\|__|\\|__|     \\|__|        \\|_______| \\|_______|     \\|__|\n";
    private static final String PREFIX = "\t> ";
    private static final String PREFIX_WARN = "\t! ";

    //https://copypastatext.com/bongo-cat-ascii/

    //endregion

    //region Constructors

    public CatbotConsoleIO() {
        this(new Scanner(System.in)::nextLine, System.out::print);
    }

    public CatbotConsoleIO(Supplier<String> in, Consumer<String> out) {
        setIn(in);
        setOut(out);
    }

    //endregion

    //region IO variables

    private Consumer<String> out;
    private Supplier<String> in;
    public void setOut(Consumer<String> consumer) {
        this.out = consumer;
    }
    public void setIn(Supplier<String> supplier) {
        this.in = supplier;
    }

    //endregion

    //region Fields

    private String lastUserInput = null;
    private Integer endOfCommand;
    private final Parser parser = Parser.by(null);

    //endregion

    //region Input-getters

    public String listen() {
        lastUserInput = in.get();
        this.endOfCommand = null;
        return lastUserInput;
    }

    public String retrieve() {
        return lastUserInput;
    }

    private void findCommand() {
        if (retrieve() != null)
            this.endOfCommand = retrieve().indexOf(" ");
    }

    public String getCommand() {
        if (this.endOfCommand == null) findCommand();
        if (this.endOfCommand == -1) return retrieve();
        else return retrieve().substring(0, this.endOfCommand);
    }

    public String getArgs() {
        if (this.endOfCommand == null) findCommand();
        if (this.endOfCommand == -1) return "";
        else return retrieve().substring(this.endOfCommand+1);
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
    }

    @Override
    public CommandArgument getNextCommand() {
        String nextLine;
        NamedParameterMap namedParameterMap;
        while (true) {
            nextLine = nextLine();
            namedParameterMap = parser.parse(nextLine);
            if (namedParameterMap.keySet().size() == 1) {
                for (String command : namedParameterMap.keySet()) {
                    return new CommandArgument(command, namedParameterMap.get(command));
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
    public void indicateInvalidIndex(int attemptedIndex, TaskList.Bounds bounds) {
        warn("i expected a number from " + bounds.lowerBound + " to " + bounds.upperBound + "...");
    }

    @Override
    public void indicateArgumentInvalid(InvalidState invalidState, NamedParameterMap namedParameterMap) {
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
    public void printTaskList(TaskList taskList) {
        int i = 1;
        int intlen = 0;
        for (int len = taskList.size(); len>0; intlen++) len/=10;
        for (Task t : taskList.getTasks()) {
            send(String.format("%" + intlen + "d", i++) + ". " + t);
        }
    }

    @Override
    public void printTaskAdded(TaskList taskList) {
        int index = taskList.size()-1;
        send("Added: " + (index+1) + ". " + taskList.getTask(index));
    }

    @Override
    public void printTaskDeleted(Task deleted) {
        send("Deleted: " + deleted);
    }

    @Override
    public void printTaskModified(TaskList taskList, int index) {
        send((index+1) + ". " + taskList.getTask(index));
    }
    //endregion

    //region Internal Helper

    private String format(String s, String prefix) {
        if (s == null) return "";
        return s.replaceAll("(^|\n)", "$1"+prefix);
    }

    private void line() {
        out.accept("───────────────────────────────────────────────────────────────────────────\n");
    }

    private void warn(String s) {
        out.accept(format(s, PREFIX_WARN) + "\n");
    }

    private void send(String s) {
        if (s == null) return;
        out.accept(format(s, PREFIX) + "\n");
    }

    private String nextLine() {
        return this.in.get();
    }

    //endregion

}
