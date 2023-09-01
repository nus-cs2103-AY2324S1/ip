package rocket;

public class FindCommand extends Command {
    private String stringToFind;

    public FindCommand (String stringToFind) {
        super(false);
        this.stringToFind  = stringToFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (String.valueOf(tasks.get(i)).contains(stringToFind)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
    }
}
