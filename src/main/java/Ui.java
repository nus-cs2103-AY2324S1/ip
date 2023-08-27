import java.util.Scanner;

/**
 * Ui encapsulates the user interface of the chat bot
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome() {
        String chatBotName = "Benedict Cucumber Badge";
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
    }
    /**
     * Reads the user's command
     * @return the user's command
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }


    /**
     * Shows the user the list of tasks
     */
    public void terminate() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the user the list of tasks
     * @param taskList  the list of tasks
     */
    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length(); i++) {
            System.out.println(i + 1 + ". " +taskList.getTask(i));
        }
    }

    /**
     * Shows the user the error message when invalid command is given
     * @param input the user's input
     */
    public void showCommandError(String input) {
        String commandWord = input.split(" ")[0];
        System.out.println("You have entered a invalid command, "
                + commandWord + " is not a valid command");
        String message = "valid commands: ";
        for (int i = 0; i < Command.validCommands.length; i++) {
            message += ("\n" + Command.validCommands[i]);
        }
        System.out.println(message);
    }



}
