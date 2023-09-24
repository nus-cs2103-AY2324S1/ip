package duke;

/**
 * Represents the types of exceptions.
 */
public enum ExceptionTypes {
    INVALIDCOMMANDEVENT("OINK! Invalid command. The command for an event should be:\n"
            + "event <task> /from yyyy-mm-dd hh:mm /to yyyy-mm-dd hh:mm"),
    INCOMPLETECOMMANDEVENT("OINK! The description of an event cannot be empty and "
            + "the start and end time/date cannot be empty."),
    INVALIDCOMMANDDEADLINE("OINK! Invalid command. The command for a deadline should be:\n"
            + "deadline <task> /by yyyy-mm-dd hh:mm"),
    INCOMPLETECOMMANDDEADLINE("OINK! The description of a deadline cannot be empty "
            + "and the deadline for the deadline cannot be empty."),
    INCOMPLETECOMMANDTODO("OINK! The description of a todo cannot be empty."),
    INVALIDTASKNUMBER("OINK! Invalid task number. The task number has to be from"
            + " one to the number of tasks in the list."),
    INCOMPLETETASKNUMBER("OINK! Task number cannot empty. Please indicate a task number"
            + " from one to the number of tasks in the list."),
    MARKEMPTYLIST("Add task to start marking task as done!"),
    UNMARKEMPTYLIST("Add task to start marking task as not done!"),
    DELETEEMPTYLIST("Add task to start deleting!"),
    INVALIDCOMMAND("OINK! I'm sorry, but I don't know what that means :-("),
    EMPTYKEYWORD("OINK! Please enter something to search."),
    INCOMPLETEUPDATEDETAILS("OINK! Please enter the task number and the new task details.\n");

    private final String errormessage;


    ExceptionTypes(String errormessage) {
        this.errormessage = errormessage;
    }

    // A method to get the error message of the exception
    public String getErrormessage() {
        return this.errormessage;
    }
}
