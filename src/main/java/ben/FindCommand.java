package ben;

import java.util.Arrays;

public class FindCommand extends Command{
    private String search;
    private final String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public void extractSearch(String command) throws EmptyDescriptionException{
        String[] words = command.toLowerCase().split("\\s+");
        String search = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        if (search.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty");
        }

        this.search = search;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws EmptyDescriptionException{
        TaskList filteredTasks = new TaskList();
        extractSearch(command);

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(search)) {
                filteredTasks.add(tasks.get(i), false);
            }
        }

        if (filteredTasks.size() == 0) {
            Ui.displayMessage("\n"  + "Sorry, no tasks seems to match: " + search + "\n" );
        } else {
            Ui.displayMessage("\n" + "Here are the matching tasks!" + "\n" + filteredTasks);
        }
    }
}
