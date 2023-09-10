public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
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

            String deletedTaskString = taskList.remove(i - 1).toString();
            ui.showDeletedTask(deletedTaskString, taskList.size());
            storage.rewriteSaveFile(taskList);
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        }
    }
}
