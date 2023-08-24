import java.util.function.Consumer;

public abstract class OutputFormatter {

    //makes class EFFECTIVELY FINAL
    private OutputFormatter() {}

    //http://www.patorjk.com/software/taag/#p=display&h=1&f=3D-ASCII&t=CAT%20BOT
    //font: 3D-ASCII; Character Width: Fitted; Character Height: Default; Text: CAT BOT
    public static String NAME =
            " ________   ________   _________        ________   ________   _________   \n" +
            "|\\   ____\\ |\\   __  \\ |\\___   ___\\     |\\   __  \\ |\\   __  \\ |\\___   ___\\ \n" +
            "\\ \\  \\___| \\ \\  \\|\\  \\\\|___ \\  \\_|     \\ \\  \\|\\ /_\\ \\  \\|\\  \\\\|___ \\  \\_| \n" +
            " \\ \\  \\     \\ \\   __  \\    \\ \\  \\       \\ \\   __  \\\\ \\  \\\\\\  \\    \\ \\  \\  \n" +
            "  \\ \\  \\____ \\ \\  \\ \\  \\    \\ \\  \\       \\ \\  \\|\\  \\\\ \\  \\\\\\  \\    \\ \\  \\ \n" +
            "   \\ \\_______\\\\ \\__\\ \\__\\    \\ \\__\\       \\ \\_______\\\\ \\_______\\    \\ \\__\\\n" +
            "    \\|_______| \\|__|\\|__|     \\|__|        \\|_______| \\|_______|     \\|__|\n";

    //https://copypastatext.com/bongo-cat-ascii/

    private static Consumer<String> output = System.out::println;

    public static void main(String[] args) {
        start();
        end();
    }

    public static void setOutputConsumer(Consumer<String> newOutput) {
        OutputFormatter.output = newOutput;
    }

    public static void start() {
        line();
        output.accept("Hiya! I'm\n" + NAME);
        line();
    }

    public static void end() {
        output.accept("Bye!");
        line();
    }

    private static void line() {
        output.accept("---------------------------------------------------------------------------");
    }
}
