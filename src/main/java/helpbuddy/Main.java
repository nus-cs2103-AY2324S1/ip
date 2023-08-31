package helpbuddy;

/**
 * A class which runs the HelpBuddy chatbot.
 */
public class Main {
    public static void main(String[] args) {
        new HelpBuddy("data/tasks.txt").run();
    }
}
