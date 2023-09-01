package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.commands.ListCommand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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
        }
    }
}
