package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.Ui;

import java.util.ArrayList;

/**
 * A class for handling find commands.
 */
public class FindCommand extends Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Finds all tasks with the given keyword and lists them out.
     *
     * @param storage
     * @param tasks
     */
    public void execute(Storage storage, ArrayList<Task> tasks) {
        String[] words = fullCommand.split(" ");

        try {
            String pattern = words[1];
            ArrayList<Task> validTasks = new ArrayList<>();

            for (Task task : tasks) {
                String description = task.getDescription();
                if (description.contains(pattern)) {
                    validTasks.add(task);
                }
            }
            System.out.println("Here are the matching tasks in your list:");
            new ListCommand().execute(storage, validTasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a find cannot be empty.");
            Ui.printLine();
        }
    }
}
