package duke.ui;

/**
 * Handles the UI.
 * @author Toh Li Yuan (A0255811H)
 */
public class ChatView {
    private static final String LINE_BREAK = "____________________________";
    public ChatView() {

    }

    /**
     * Starts the text UI.
     */
    public void startMessage() {
        System.out.println(String.format("%s\nKnowledgeYuan, at your service!\nWhat can I do for you today?\n%s",
                LINE_BREAK, LINE_BREAK));
    }

    /**
     * Displays the stylised string result from actions to the console.
     *
     * @param str the string to be displayed.
     */
    public void displayOutput(String str) {
        System.out.println(beautifyString(str));
    }

    /**
     * Displays the basic string result from actions to the console.
     *
     * @param str the string to be displayed.
     */
    public void displayBasic(String str) {
        System.out.println(str);
    }

    private String beautifyString(String str) {
        return String.format("\t%s\n%s\n%s", LINE_BREAK, str, LINE_BREAK).replace("\n", "\n\t");
    }

}
