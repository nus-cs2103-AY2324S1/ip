public class HelpMessage {
    public static void displayHelpMessage() {
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
}
