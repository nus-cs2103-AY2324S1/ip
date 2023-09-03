package LogicHandlers.CommandHandlers;

import Models.TaskArray;

import static Ui.BasicOutputPrinter.printBasicOutput;

/**
 * HelpCommandHandler handles all 'help' commands.
 */
public class HelpCommandHandler implements Command{
    private TaskArray tasks;

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
    public void parseCommandContent(String commandContent) {
        String help = " Hello! I'm EGGBOT!\n\n" +
                " Adding of tasks:\n" +
                " ToDo task: 'todo [Task]'\n" +
                " Deadline task: 'deadline [Task /Deadline (dd-mm-yyyy hhmm)]'\n" +
                " Event task: 'event [Task /Start Date (dd-mm-yyyy hhmm)/End Date (dd-mm-yyyy hhmm)]'\n\n" +
                " Other commands:\n" +
                " To view tasks, type 'list' \n" +
                " To mark a task as 'done', type 'mark [index]' \n" +
                " To mark a task as 'undone', type 'unmark [index]' \n" +
                " To delete a task, type 'delete [index]' \n" +
                " To view a list of commands, type 'help' \n" +
                " To exit, type 'bye' or 'exit'";

        printBasicOutput(help);
    }
}

