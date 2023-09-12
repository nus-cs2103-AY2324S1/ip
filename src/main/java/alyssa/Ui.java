package alyssa;

/**
 * This class helps Alyssa interact with the user.
 */
public class Ui {
    /**
     * Sends a greeting message via System.out.println.
     */
    protected String getGreeting() {
        String output = "Hello! I'm Alyssa!\n";
        output += "What can I do for you?\n";
        return output;
    }
    /**
     * Returns a goodbye message.
     */
    protected String goodbye() {
        return "Bye. Hope to see you again soon! Subsequent messages entered will cause the program to close.";
    }
    /**
     * Returns a help message, informing users of commands and syntax.
     */
    protected String printHelpMessage() {
        String output = "Commands:\n";
        output += "Add todo: todo {description}\n";
        output += "Add deadline: deadline {description} /by {YYYY-MM-DD}\n";
        output += "Add event: event {description} /from {date/time} /to {date/time}\n";
        output += "List tasks: list\n";
        output += "Mark task as done: mark {task number}\n";
        output += "Unmark task: mark {task number}\n";
        output += "Delete task: delete {task number}\n";
        output += "Find task: find {keyword}\n";
        output += "Marking: mark {task number}\n";
        output += "Close program: bye\n";
        output += "Help Page: help\n";
        return output;
    }
}
