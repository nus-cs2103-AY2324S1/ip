package duke;

/**
 * FindTask class with a field keyword.
 */
public class FindTask extends Command {
    private String keyword;

    public FindTask(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) {
        TaskList found = new TaskList();
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            String name = task.getName();
            String[] info = name.split("\\s+");
            for (int j = 0; j < info.length; j++) {
                if (info[j].equalsIgnoreCase(this.keyword)) {
                    found.add(task);
                }
            }
        }
        return ui.foundMessage(found);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
