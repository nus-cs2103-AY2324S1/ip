package bruno;

/**
 * The UI class is responsible for the display of messages, i.e, it controls the user-interface of the task
 * management system.
 */
public class UI {

    /**
     * Displays the divider lines before and after each command.
     */
    public void displayLines() {
        for (int i = 0; i < 20; i++) {
            System.out.print((i == 0 ? "-" : " -"));
        }
        System.out.println();
    }

    /**
     * Displays the greeting at the start of the program.
     */
    public String displayGreeting() {
        String name = "Bruno";
        return "Woof Woof! I'm " + name + " 🐾" + "\nHow can I help you?";
    }

    /**
     * Displays the "bye" message when the command "bye" is entered.
     */
    public String displayBye() {
        return "Bye Bye! Hope to see you again soon! 🐶";
    }

    /**
     * Displays the appropriate messages for each command.
     *
     * @param taskInfo The command message.
     */
    public void displayMessage(String taskInfo) {
        System.out.println(taskInfo);
    }
}
