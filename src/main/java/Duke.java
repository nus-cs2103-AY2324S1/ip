import java.util.ArrayList;
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
        addAndList();
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
     * Allows adding of item into an ArrayList and list all items in the ArrayList.
     */
    public static void addAndList() {
        boolean isChatting = true;
        Scanner inputs = new Scanner(System.in);
        String command;
        ArrayList<String> taskList = new ArrayList<>();

        while (isChatting) {
            command = inputs.nextLine();
            switch(command) {
                case ("bye"): {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Goodbye, it was nice chatting with you.");
                    System.out.println(HORIZONTAL_LINE);
                    isChatting = false;
                    break;
                }
                case ("list"): {
                    System.out.println(HORIZONTAL_LINE);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i));
                    }
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
                default: {
                    taskList.add(command);
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("I have added \"" + command + "\" to the list.");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
            }
        }
    }
}
