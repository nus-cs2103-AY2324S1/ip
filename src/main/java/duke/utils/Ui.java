package duke.utils;

/**
 * Represents the user interface of the bot.
 */
public class Ui {
    /**
     * Prints a horizontal line for formatting purposes.
     */
    public void printHorizontalLine() {
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Prints a message from the bot. It will be indented.
     * @param msg The message to be displayed.
     */
    public void printBotMessage(String msg) {
        printHorizontalLine();
        System.out.println(msg);
        printHorizontalLine();
    }

    /**
     * Prints a greeting message from the bot.
     */
    public void printGreeting() {
        printBotMessage("Hello from\n" + "DUKE");
    }

    /**
     * Prints a farewell message from the bot.
     */
    public void printFarewell() {
        printBotMessage("Bye. Hope to see you again soon! \uD83D\uDD19 \uD83D\uDD1B \uD83D\uDD1D");
    }

    /**
     * Prints all the tasks in the list.
     */
    public void printTasks(TaskList tasks) {
        printBotMessage("Here are the tasks in your list:\n" + tasks);
    }

}
