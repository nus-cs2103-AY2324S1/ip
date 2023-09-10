public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        String[] taskStrings = new String[taskList.size()];

        for (int i = 0; i < taskStrings.length; i++) {
            taskStrings[i] = taskList.getTaskString(i);
        }

        ui.showTaskList(taskStrings);
    }
}
