package LogicHandlers.CommandHandlers;

import Models.TaskArray;

import static Printers.BasicOutputPrinter.printBasicOutput;

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
                " To add a ToDo task, type 'todo [Task]'\n" +
                " To add a Deadline task, type 'deadline [Task /Deadline]'\n" +
                " To add an Event task, type 'event [Task /Start Date/End Date]'\n" +
                " To view tasks, type 'list' \n" +
                " To mark a task as 'done', type 'mark [index]' \n" +
                " To mark a task as 'undone', type 'unmark [index]' \n" +
                " To delete a task, type 'delete [index]' \n" +
                " To view a list of commands, type 'help' \n" +
                " To exit, type 'bye' or 'exit'";

        printBasicOutput(help);
    }
}

