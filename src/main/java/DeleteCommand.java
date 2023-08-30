public class DeleteCommand extends Command {

    private String commandBody;

    public DeleteCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = String.format("OOPS!!! The command for delete task is invalid.");
        int task_num;
        try {
            if (!commandBody.equals("all")) {
                task_num = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(Keyword.DELETE, ui);
                storage.changeFile(Keyword.DELETE, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, "delete");
        }
        taskList.deleteTask(task_num - 1, ui);
        storage.changeFile(Keyword.DELETE, task_num - 1);
    }
}
