package ui;

/**
 * Class for chatbot to create standardised replies with ui
 */
public class Reply {
    private String indent = "     ";
    private String section = indent + "________________________________________\n";
    private static Reply reply = null;

    /**
     * private constructor
     */
    private Reply() {
        //Print intro
        System.out.println(section
                + indent + "Hello! I'm chatbot.evan.Evan, your personal task planning assistant\n"
                + indent + "What can I do for you?\n\n"
                + indent + "List of available commands:\n"
                + indent + "todo: create a new todo task\n"
                + indent + "deadline: create a new deadline task\n"
                + indent + "event: create a new event task\n"
                + indent + "mark: mark a task as complete\n"
                + indent + "unmark: mark a task as incomplete\n"
                + indent + "delete: delete a task from the list\n"
                + section);
    }

    /**
     * factory method to enforce one instance of the reply class
     * @return an instance of Reply
     */
    public static Reply init() {
        if (reply == null) {
            reply = new Reply();
        }
        return reply;
    }

    /**
     * Takes in a dialog and prints it our in a standardised ui format
     * @param dialog dialog of the chatbot reply
     */
    public void printDialog(String dialog) {
        System.out.println(section + indent + dialog + "\n" + section);
    }
}
