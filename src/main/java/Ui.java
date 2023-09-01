import Exceptions.DukeException;

/**
 * Represents the User Interface of the Duke App.
 * Each <Code>Ui</Code> method displays a message to the user.
 */
public class Ui {
    public void showWelcome() {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        System.out.println("Hello!\n" + logo);
    }

    public void showDukeError(DukeException e) {
        System.out.println(":( Chewy can't understand! " + e.getMessage());
    }

    public void displayHelpMessage() {
        System.out.println("List of available commands:");
        System.out.println(" - todo <description>: Add a new todo task");
        System.out.println(" - deadline <description> /by <date>: Add a new deadline task");
        System.out.println(" - event <description> /from <start> /to <end>: Add a new event task with start and end time");
        System.out.println(" - list: Display the list of tasks");
        System.out.println(" - mark <taskNumber>: Mark a task as done");
        System.out.println(" - unmark <taskNumber>: Unmark a task as done");
        System.out.println(" - bye: Exit Chewy");
        // Add more commands in the future
    }

    public void showException(Exception e) {
        System.out.println("Something unexpected happened. try 'help' to see a list of commands");
    }
}
