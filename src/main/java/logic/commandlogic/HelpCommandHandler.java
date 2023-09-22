package logic.commandlogic;

import models.TaskArray;

/**
 * HelpCommandHandler handles all 'help' commands.
 */
public class HelpCommandHandler implements Command{
    private TaskArray tasks;
    private String help = " Hello! I'm EGGBOT!\n\n" +
            " >>> Adding of tasks:\n" +
            " 1. ToDo task: 'todo [Task]'\n" +
            " 2. Deadline task: 'deadline [Task /Deadline (dd-mm-yyyy hhmm)]'\n" +
            " 3. Event task: 'event [Task /Start Date (dd-mm-yyyy hhmm)/End Date (dd-mm-yyyy hhmm)]'\n\n" +
            " >>> Other commands:\n" +
            " 4. To view tasks, type 'list' \n" +
            " 5. To mark a task as 'done', type 'mark [index]' \n" +
            " 6. To mark a task as 'undone', type 'unmark [index]' \n" +
            " 7. To delete a task, type 'delete [index]' \n" +
            " 8. To find a task, type 'find <a word in the task name>' \n" +
            " 9. To view a list of commands, type 'help' \n" +
            " 10. To exit, type 'bye' or 'exit'";

    /**
     * Constructor for HelpCommandHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public HelpCommandHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the help message.
     * commandContent here is redundant.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public String parseCommandContent(String commandContent) {
        return (help);
    }
}

