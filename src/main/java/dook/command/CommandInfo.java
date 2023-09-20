package dook.command;

/**
 * Enum containing a list of all commands and their descriptions.
 */
public enum CommandInfo {
        bye("Exits the program"), list("Displays the current tasks"),
        mark("Marks selected task as done."), unmark("Marks selected task as undone."),
        todo("Adds a task."), deadline("Adds a task with a deadline."),
        event("Adds a task with a start and end time."), delete("Deletes selected task from list."),
        save("Saves the current task list to a file"), before("Displays all tasks before a certain date."),
        after("Displays all tasks after a certain date."), during("Displays all tasks during a certain date."),
        find("Searches for a task with matching keyword."), invalid("You entered an invalid command."),
        alias("Adds a new keyword to corresponding command");

    private final String description;

    CommandInfo(String description) {
        this.description = description;
    }
    public String getName() {
        return this.name();
    }
    @Override
    public String toString() {
        return this.name() + ": " + this.description;
    }
}
