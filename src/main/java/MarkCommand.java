public class MarkCommand extends Command {

    public MarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        if (commandDescription.isEmpty()) {
            throw new TasketException("The task index cannot be empty");
        }

        try {
            int i = Integer.parseInt(commandDescription);

            if (i <= 0) {
                throw new TasketException("The task index cannot be less than 1");
            } else if (i > taskList.size()) {
                throw new TasketException("The task index cannot exceed the list");
            }

            taskList.mark(i - 1);
            ui.showMarkedTask(taskList.getTaskString(i - 1));
            storage.rewriteSaveFile(taskList);
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        }
    }
}
