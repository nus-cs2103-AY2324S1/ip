import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class IOFormatter {

    //region Singleton class

    public static IOFormatter ioFormatter = new IOFormatter();
    private IOFormatter() {}

    //endregion

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

    //https://copypastatext.com/bongo-cat-ascii/

    //endregion

    //region IO variables

    private static Consumer<String> out = System.out::print;
    private static Supplier<String> in = new Scanner(System.in)::nextLine;
    public static void setOut(Consumer<String> consumer) {
        IOFormatter.out = consumer;
    }
    public static void setIn(Supplier<String> supplier) {
        IOFormatter.in = supplier;
    }

    //endregion

    //region Fields

    private String lastUserInput = null;
    private Integer endOfCommand;

    //endregion

    //region User-Facing Helpers

    private void line() {
        out.accept("───────────────────────────────────────────────────────────────────────────\n");
    }

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

    //region Out-putters

    public void start() {
        line();
        out.accept("Hiya! I'm\n" + NAME + "\n");
        line();
    }

    public void end() {
        send("Bye!");
        line();
    }

    public void send(String s) {
        out.accept(format(s) + "\n");
    }

    public void send(Object o) {
        send(o.toString());
    }

    public void send(TaskList taskList) { //todo empty list
        int i = 1;
        int intlen = 0;
        for (int len = taskList.size(); len>0; intlen++) len/=10;
        for (Task t : taskList) {
            send(String.format("%" + intlen + "d", i++) + ". " + t);
        }
    }

    public void unexpected() {
        send("I can't do that :(");
    }

    //endregion

    //region Output: Internal Helpers

    private String format(String s) {
        if (s == null) return "";
        return s.replaceAll("(^|\n)", "$1"+PREFIX);
    }

    //endregion
}
