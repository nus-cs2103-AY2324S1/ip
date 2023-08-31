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
    public void displayGreeting() {
        String logo = "                ;~~,__,\n" + ":-….,———-‘`----/   ._.*\n" + " `-,,,   BRUNO   ,’\n"
                + "     ;   ,~.——;  /\n" + "     :  |     :  |\n" + "     `_ ’     `_ ‘";
        System.out.println(logo);
        String name = "Bruno";
        System.out.println("Woof Woof! I'm " + name + " 🐾");
        System.out.println("How can I help you?");
        System.out.println("[Please not that date/time must be in \"yyyy-MM-dd HH:mm\" format]");
    }

    /**
     * Displays the "bye" message when the command "bye" is entered.
     */
    public void displayBye() {
        System.out.print("\t");
        System.out.println("Bye Bye! Hope to see you again soon! 🐶");
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
