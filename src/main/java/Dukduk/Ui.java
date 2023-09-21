package dukduk;

import java.util.ArrayList;

/**
 * Represents the user interface (UI) for the Dukduk chatbot and return strings.
 */
public class Ui {

    /**
     * Returns a help message for incorrect inputs.
     * 
     * @return a help message for incorrect inputs.
     */
    public String printHelpMessage() {
        StringBuilder commands = new StringBuilder("QUACKKK!!! Invalid command. " +
                "Try the following commands instead:\n");

        String[] validCommands = {
                "~ todo <task>",
                "~ deadline <task> /by yyyy-mm-dd hhmm",
                "~ event <task> /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm",
                "~ list",
                "~ mark <task number>",
                "~ unmark <task number>",
                "~ delete <task number>",
                "~ find <keyword>",
                "~ bye"
        };

        for (String command : validCommands) {
            commands.append(command).append("\n");
        }
        return commands.toString();
    }

    /**
     * Returns a message when the user exits the application.
     * 
     * @return a message when the user exits the application.
     */
    public String printExit() {
        return "Quackkk byeee!! Hope to see you again soon!";
    }

    /**
     * Returns a list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     * @return a list of tasks.             
     */
    public String printTasks(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder("Quackkk, here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return message.toString();
    }

    /**
     * Returns a message when a task is added to the list.
     *
     * @param tasks The list of tasks after adding a new task.
     * @return a message when a task is added to the list.             
     */
    public String addTask(ArrayList<Task> tasks) {
        Task addedTask = tasks.get(tasks.size() - 1);
        return String.format("Quackkk, got it! I've added this task:\n%s\n" +
                        "Now you have %d tasks in the list.",
                addedTask, tasks.size());
    }

    /**
     * Returns a message when a task is marked as done.
     *
     * @param tasks     The list of tasks after marking a task as done.
     * @param taskIndex The index of the marked task.
     * @return a message when a task is marked as done.
     */
    public String markAsDone(ArrayList<Task> tasks, int taskIndex) {
        Task markedTask = tasks.get(taskIndex);
        return String.format("Quackkk!!! Nice! I've marked this task as done:\n[%s] %s",
                markedTask.getStatusIcon(), markedTask.getDescription());
    }

    /**
     * Returns a message when a task is marked as not done.
     *
     * @param tasks     The list of tasks after marking a task as not done.
     * @param taskIndex The index of the marked task.
     * @return a message when a task is marked as not done.                
     */
    public String markAsNotDone(ArrayList<Task> tasks, int taskIndex) {
        Task markedTask = tasks.get(taskIndex);
        return String.format("Quack, ok! I've marked this task as not done yet:\n[%s] %s",
                markedTask.getStatusIcon(), markedTask.getDescription());
    }

    /**
     * Returns a list of tasks found from find method, else prints no tasks found.
     *
     * @param matchingTasks The list of matching tasks to be printed.
     * @return the list of matching tasks.                     
     */
    public String printTasksIfFound(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "QUACKKK!!! No matching tasks found.";
        } else {
            StringBuilder message = new StringBuilder("Quackkk, here are the matching tasks " +
                    "in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
            return message.toString();
        }
    }

    /**
     * Returns an error message when an exception occurs.
     *
     * @param e The DukdukException that occurred.
     * @return error message when an exception occurs.
     */
    public String printErrorMsg(DukdukException e) {
        return e.getMessage();
    }
}
