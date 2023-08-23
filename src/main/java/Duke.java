import java.util.Scanner;

public class Duke {

    private static final String NAME = "Sisyphus";
    private static final String HORIZONTAL_LINE = "_________________________________";
    private static final String LOGO = "\n" +
            "      ,-'\"\"\"`-.\n" +
            "    ,'         `.\n" +
            "   /        `    \\\n" +
            "  (    /          )\n" +
            "  |             \" |\n" +
            "  (               )\n" +
            " `.\\\\          \\ /\n" +
            "   `:.     , \\ ,\\ _\n" +
            " WE   `:-.___,-`-.{\\)\n" +
            " MUST  `.        |/ \\\n" +
            " GO      `.        \\ \\\n" +
            " ON        `-.     _\\,)\n" +
            "              `.  |,-||\n" +
            "                `.|| ||\n";
    public static void main(String[] args) {
        greet();
        echo();
    }

    /**
     * Greets user with name.
     */
    public static void greet() {
        System.out.println(LOGO);
        System.out.println("Hello, I'm " + NAME + ".");
        System.out.println("What can I do for you? Do you want to roll a boulder?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Echoes command of users until user enters bye.
     */
    public static void echo() {
        boolean isChatting = true;
        Scanner inputs = new Scanner(System.in);
        String command;

        while (isChatting) {
            command = inputs.nextLine();
            if (command.equals("bye")) {
                System.out.println("Goodbye, it was nice chatting with you.");
                break;
            }
            System.out.println(HORIZONTAL_LINE);
            System.out.println("     " + command);
            System.out.println(HORIZONTAL_LINE);
        }
    }
}
