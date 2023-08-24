import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public abstract class IOFormatter {

    //makes class EFFECTIVELY FINAL
    private IOFormatter() {}

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

    private static Consumer<String> out = System.out::print;
    private static Supplier<String> in = new Scanner(System.in)::nextLine;
    public static void setOut(Consumer<String> consumer) {
        IOFormatter.out = consumer;
    }
    public static void setIn(Supplier<String> supplier) {
        IOFormatter.in = supplier;
    }

    private static String lastUserInput = null;

    public static void start() {
        line();
        out.accept("Hiya! I'm\n" + NAME + "\n");
        line();
    }

    public static void end() {
        send("Bye!");
        line();
    }

    private static void line() {
        //out.accept("---------------------------------------------------------------------------\n");
        out.accept("───────────────────────────────────────────────────────────────────────────\n");
    }

    public static String listen() {
        lastUserInput = in.get();
        return lastUserInput;
    }

    public static void send(String s) {
        out.accept(format(s) + "\n");
    }

    private static String format(String s) {
        return s.replaceAll("(^|\n)", "$1"+PREFIX);
    }

    public static String retrieve() {
        return lastUserInput;
    }
}
