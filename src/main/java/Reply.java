public class Reply {
    private String indent = "     ";
    private String section = indent + "________________________________________\n";
    private static Reply reply = null;

    private Reply() {
        //Print intro
        System.out.println(section
                + indent + "Hello! I'm Evan, your personal task planning assistant\n"
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

    public static Reply init() {
        if (reply == null) {
            reply = new Reply();
        }
        return reply;
    }

    public void printDialog(String dialog) {
        System.out.println(section + indent + dialog + "\n" + section);
    }
}
