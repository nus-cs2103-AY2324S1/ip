package duke;

/**
 * This class helps Duke interact with the user.
 */
public class Ui {
    /**
     * Prints out the greeting message for when the chatbot is first launched.
     * @return The greeting message.
     */
    public static String greet() {
        return "Hello! I'm POPOOH!!\n"
                + "What can I do for you?\n";
    }
    /**
     * Prints out the exit message for when the chatbot.
     * @return The exit message.
     */
    public static String exit() {
        return "Glad that I could help you!\n"
                + "See ya next time hehe :)";
    }
}
